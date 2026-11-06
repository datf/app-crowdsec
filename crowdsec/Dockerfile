ARG BUILD_FROM=ghcr.io/hassio-addons/debian-base:9.5.2
# hadolint ignore=DL3006
FROM ${BUILD_FROM}

# Set shell
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

ENV LANG=C.UTF-8
ENV DEBIAN_FRONTEND=noninteractive
ENV DEBCONF_NOWARNINGS="yes"

ENV CROWDSEC_VERSION=1.8.2

# hadolint ignore=DL3008,DL3015
RUN apt-get update && apt-get install -y -q --install-recommends --no-install-suggests \
    ca-certificates \
    tzdata \
    curl \
    rsync=3.5.0+ds1-0+deb13u1 \
    bash=5.2.37-2+b10 \
    systemd=257.13-1~deb13u1 \
    && curl -s https://packagecloud.io/install/repositories/crowdsec/crowdsec/script.deb.sh?any=true | bash \
    && apt-get update \
    && mv /usr/bin/systemctl /usr/bin/systemctl.bak \
    && printf '#!/bin/sh\nexit 0\n' > /usr/bin/systemctl \
    && chmod +x /usr/bin/systemctl \
    && apt-get install -y --no-install-recommends \
        crowdsec=${CROWDSEC_VERSION} \
    && rm -f /usr/bin/systemctl \
    && mv /usr/bin/systemctl.bak /usr/bin/systemctl \
    && apt-get autoremove -y \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY rootfs /

ENV XDG_CONFIG_HOME=/config

# Build arguments
ARG BUILD_ARCH
ARG BUILD_DATE
ARG BUILD_DESCRIPTION
ARG BUILD_NAME
ARG BUILD_REF
ARG BUILD_REPOSITORY
ARG BUILD_VERSION

LABEL \
    io.hass.name="${BUILD_NAME}" \
    io.hass.description="${BUILD_DESCRIPTION}" \
    io.hass.arch="${BUILD_ARCH}" \
    io.hass.type="addon" \
    io.hass.version=${BUILD_VERSION} \
    org.opencontainers.image.title="${BUILD_NAME}" \
    org.opencontainers.image.description="${BUILD_DESCRIPTION}" \
    org.opencontainers.image.source="https://github.com/${BUILD_REPOSITORY}" \
    org.opencontainers.image.licenses="MIT" \
    org.opencontainers.image.created=${BUILD_DATE} \
    org.opencontainers.image.revision=${BUILD_REF} \
    org.opencontainers.image.version=${BUILD_VERSION}
