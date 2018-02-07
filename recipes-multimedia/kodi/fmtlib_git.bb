# Copyright (C) 2018 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=c2e38bc8629eac247a73b65c1548b2f0"

inherit pkgconfig cmake

SRCREV = "8cf30aa2be256eba07bb1cefb998c52326e846e7"
SRC_URI = "git://github.com/fmtlib/fmt.git;branch=master"
S = "${WORKDIR}/git"

FILES_${PN}-dev += "${libdir}/cmake/"
