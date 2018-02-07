# Copyright (C) 2018 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Kodi Media Center -- TexturePacker"

LICENSE = "CLOSED"

FILESPATH =. "${FILE_DIRNAME}/kodi:"

DEPENDS = " \
    cmake-native \
    giflib-native \
    libpng-native \
"

SRCREV = "3316cd1adbbbd68b962558c635723f8259e19c9f"
SRC_URI = "git://github.com/xbmc/xbmc.git;branch=master"
S = "${WORKDIR}/git/tools/depends/native/TexturePacker/src"

inherit autotools-brokensep native gettext pkgconfig

do_configure_prepend() {
    # remove @STATIC_FLAG@
    sed -i '/STATIC_FLAG/d' ${S}/Makefile.am
}
