# Copyright (C) 2018 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Kodi Media Center"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=930e2a5f63425d8dd72dbd7391c43c46"

DEPENDS = " \
    cmake-native \
    curl-native \
    gperf-native \
    jsonschemabuilder-native \
    nasm-native \
    swig-native \
    unzip-native \
    yasm-native \
    zip-native \
    texturepacker-native \
    avahi \
    boost \
    bzip2 \
    crossguid \
    curl \
    dcadec \
    enca \
    expat \
    faad2 \
    ffmpeg \
    fontconfig \
    fribidi \
    giflib \
    jasper \
    libass \
    libcdio \
    libcec \
    libmad \
    libmicrohttpd \
    libmms \
    libmms \
    libmodplug \
    libpcre \
    libplist \
    libsamplerate0 \
    libsdl-image \
    libsdl-mixer \
    libsquish \
    libssh \
    libtinyxml \
    libusb1 \
    libxslt \
    lzo \
    mpeg2dec \
    python \
    samba \
    sqlite3 \
    taglib \
    virtual/egl \
    virtual/libsdl \
    wavpack \
    yajl \
    zlib \
    rapidjson \
    fmtlib \
"

PROVIDES = "xbmc"

SRCREV = "3316cd1adbbbd68b962558c635723f8259e19c9f"
SRC_URI = "git://github.com/xbmc/xbmc.git;branch=master \
            file://0001-cmake-fix-JsonSchemaBuilder-path.patch \
            file://0001-cmake-use-host-native-java.patch \
            file://0001-cmake-add-sysroot-in-core_link_library.patch \
"

inherit cmake gettext pythonnative distro_features_check

S = "${WORKDIR}/git"

EXTRA_OECMAKE = " \
    -DENABLE_INTERNAL_FFMPEG=OFF \
    -DENABLE_INTERNAL_CROSSGUID=OFF \
    -DENABLE_OPENSSL=ON \
    -DENABLE_UDEV=ON \
    -DENABLE_DBUS=ON \
    -DENABLE_XSLT=ON \
    -DENABLE_LIRC=ON \
    -DENABLE_EVENTCLIENTS=ON \
    -DENABLE_LDGOLD=ON \
    -DENABLE_DEBUGFISSION=OFF \
    ${@bb.utils.contains("DISTRO_FEATURES", "x11", "-DCORE_PLATFORM_NAME=x11", \
        bb.utils.contains("DISTRO_FEATURES", "wayland", \
        "-DCORE_PLATFORM_NAME=wayland -DWAYLAND_RENDER_SYSTEM=gles", \
        "-DCORE_PLATFORM_NAME=gbm", d), d)} \
    -DENABLE_UPNP=ON \
    -DENABLE_SSH=ON \
    -DWITH_TEXTUREPACKER=TexturePacker \
"

# for python modules
export HOST_SYS
export BUILD_SYS
export TARGET_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export PYTHON_DIR
export STAGING_BINDIR_NATIVE
export STAGING_DIR_HOST

do_compile_prepend() {
    for i in $(find . -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' $i
    done

    for i in $(find . -name "*.mak*" -o    -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir):-rpath ${libdir}:g' $i
    done
}

INSANE_SKIP_${PN} = "rpaths already-stripped"

FILES_${PN} += "${datadir}/xsessions ${datadir}/icons ${libdir}/kodi ${datadir}/kodi ${libdir}/python2.7"
FILES_${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

# kodi uses some kind of dlopen() method for libcec so we need to add it manually
# OpenGL builds need glxinfo, that's in mesa-demos
RDEPENDS_${PN}_append = " libcec \
    python \
    python-ctypes \
    python-lang \
    python-re \
    python-netclient \
    python-html \
    python-difflib \
    python-json \
    python-zlib \
    python-shell \
    python-sqlite3 \
    python-compression \
    libcurl \
    ${@bb.utils.contains('PACKAGECONFIG', 'x11', 'xrandr xdpyinfo', '', d)} \
"

RDEPENDS_${PN}_append_libc-glibc = " glibc-charmap-ibm850 \
    glibc-gconv-ibm850 \
    glibc-gconv-unicode \
    glibc-gconv-utf-32 \
    glibc-charmap-utf-8 \
    glibc-localedata-en-us \
"

RPROVIDES_${PN} += "xbmc"
