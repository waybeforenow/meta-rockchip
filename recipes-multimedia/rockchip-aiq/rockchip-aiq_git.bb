# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DEFAULT_PREFERENCE = "-1"

include rockchip-aiq.inc

SRCBRANCH ?= "master"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/rockchip-linux/gstreamer-rockchip-extra.git;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"
