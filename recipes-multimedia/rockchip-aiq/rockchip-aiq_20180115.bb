# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

include rockchip-aiq.inc

TAG = "release_${PV}"
SRC_URI = " \
	git://github.com/rockchip-linux/gstreamer-rockchip-extra.git;tag=${TAG};nobranch=1 \
"

S = "${WORKDIR}/git"
