LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRCREV = "39ff30baa139dc1d9bab991dc8b764e06bafa175"
SRC_URI = "git://git@github.com/peanutsandwich/kcsd.git;protocol=ssh"

PV = "1.0+git${SRCPV}"

inherit autotools pkgconfig
inherit obmc-phosphor-systemd

S = "${WORKDIR}/git"

DEPENDS += "autoconf-archive-native"
DEPENDS += "systemd"
RDEPENDS_${PN} += "libsystemd"
RRECOMMENDS_${PN} += "phosphor-ipmi-host"

SRC_URI += "file://70-kcsd.rules"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "phosphor-kcsd@.service"

do_install_append() {
        install -d ${D}/${base_libdir}/udev/rules.d/
        install ${WORKDIR}/70-kcsd.rules ${D}/${base_libdir}/udev/rules.d/
}
