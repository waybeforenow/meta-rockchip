# Copyright (C) 2014 NEO-Technologies
# Released under the MIT license (see COPYING.MIT for the terms)

SOC_FAMILY = "rk3066"

require conf/machine/include/tune-cortexa9.inc
require conf/machine/include/soc-family.inc

MACHINEOVERRIDES_EXTENDER  = "mali-gpu:mali-utgard"

PREFERRED_PROVIDER_virtual/kernel = "linux-rockchip"
PREFERRED_VERSION_linux-rockchip = "4.4"

SERIAL_CONSOLES = "115200;ttyS2"
KERNEL_IMAGETYPE = "zImage"
KBUILD_DEFCONFIG = "rockchip_linux_defconfig"

PREFERRED_PROVIDER_virtual/bootloader ?= ""

IMAGE_FSTYPES += "ext4 squashfs"
