# Home Assistant App: CrowdSec

CrowdSec is a free, open-source, and collaborative Intrusion Prevention System (IPS) and Security Engine for Home Assistant.

For the full app documentation, including detailed installation instructions, configuration options (`collections`, `parsers`, `enable_local_api`, `enable_online_api`, `trusted_ips`, `log_level`), log acquisition (`acquis.d`), Community Blocklists, AppSec (WAF) setup, `cscli` commands, and integrating with [Caddy Custom](https://github.com/datf/hassio-caddy-custom), please refer to the documentation file:

**[Read the Full App Documentation here](crowdsec/DOCS.md)**

## Installation Quick Start

This app is available through a custom repository. To install it:

1. Follow [this link](https://my.home-assistant.io/redirect/supervisor_add_addon_repository/?repository_url=https%3A%2F%2Fgithub.com%2Fdatf%2Fhassio-addons) to add the repository and click Add.
2. Search for **CrowdSec** and click Install.
3. Review the configuration options, start the app, and set up your log acquisition files and bouncer API keys.

_Please read the [full documentation](crowdsec/DOCS.md) before starting the app to ensure it is configured securely._
