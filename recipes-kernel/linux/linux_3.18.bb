# Copyright (C) 2014 Romain Perier
# Released under the MIT license (see COPYING.MIT for the terms)

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

TAG = "next-20140917"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git;tag=${TAG} \
	file://defconfig"

LINUX_VERSION = "3.18"
LINUX_VERSION_EXTENSION = "${TAG}"
PR = "r1"
PV = "${LINUX_VERSION}+git-${TAG}"

# Only support Radxa Rock for now, other boards will come later
COMPATIBLE_MACHINE = "radxa-rock"

# Build the devicetree blob in kernel_do_compile
KERNEL_ALT_IMAGETYPE = "rk3188-radxarock.dtb"
# The resulting image to be deployed in DEPLOY_IMAGE_DIR
KERNEL_OUTPUT = "${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE}-dtb"

do_compile_append() {
    cat ${B}/arch/${ARCH}/boot/${KERNEL_IMAGETYPE} ${B}/arch/${ARCH}/boot/dts/${KERNEL_ALT_IMAGETYPE} > ${KERNEL_OUTPUT}
}
