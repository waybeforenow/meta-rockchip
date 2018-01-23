# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "AIQ library Binary for Rockchip ISP"
SECTION = "libs"
LICENSE = "CLOSED"

DEPENDS = "patchelf-native"

CPU_TYPE = ""
python __anonymous() {
    tunes = d.getVar("TUNE_FEATURES", True)
    if not tunes:
        return

    if tunes == "aarch64":
        d.setVar("CPU_TYPE", "aarch64")
    else:
        d.setVar("CPU_TYPE", "arm")

    if tunes == "aarch64":
        return
    if "callconvention-hard" not in tunes:
        pkgn = d.getVar("PN", True)
        pkgv = d.getVar("PV", True)
        raise bb.parse.SkipPackage("%s-%s ONLY supports hardfp mode for now" % (pkgn, pkgv))
}

INSANE_SKIP_${PN} = "already-stripped ldflags dev-so"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_install() {
	install -m 755 -d ${D}/${libdir}

	# Workaround: librk_aiq.so provided by rk having no SONAME field in itroot
	# so add it to fix rdepends problems
	cp ${S}/gst-libs/rkisp1/librk_aiq.so.${CPU_TYPE} ${S}/gst-libs/rkisp1/librk_aiq.so.1
	patchelf --set-soname "librk_aiq.so" ${S}/gst-libs/rkisp1/librk_aiq.so.1

	oe_soinstall ${S}/gst-libs/rkisp1/librk_aiq.so.1 ${D}/${libdir}
}

PACKAGES = "${PN}"
FILES_${PN} += "${libdir}/*.so"
