---
common:
  daemonize: false
  log_media: stdout
  log_level: "{{ .log_level }}"
  log_dir: /var/log/
config_paths:
  config_dir: "{{ .config_dir }}"
  data_dir: "{{ .data_dir }}"
  simulation_path: "{{ .config_dir }}/simulation.yaml"
  hub_dir: "{{ .config_dir }}/hub/"
  index_path: "{{ .config_dir }}/hub/.index.json"
  notification_dir: "{{ .config_dir }}/notifications/"
  plugin_dir: /usr/lib/crowdsec/plugins/
  pattern_dir: "{{ .config_dir }}/patterns/"
plugin_config:
  user: nobody
  group: nobody
cscli:
  output: human
db_config:
  log_level: info
  type: sqlite
  db_path: "{{ .data_dir }}/crowdsec.db"
  flush:
    max_items: 5000
    max_age: 7d
  use_wal: true
crowdsec_service:
  enable: true
  acquisition_path: "/etc/crowdsec/acquis.yaml"
  acquisition_dir: "{{ .config_dir }}/acquis.d"
  parser_routines: 1
api:
  client:
    insecure_skip_verify: false
    credentials_path: "{{ .config_dir }}/local_api_credentials.yaml"
  server:
    log_level: "{{ .log_level }}"
    listen_uri: 0.0.0.0:8080
    profiles_path: "{{ .config_dir }}/profiles.yaml"
    trusted_ips:
      {{- range .trusted_ips }}
      - {{ . }}
      {{- end }}
    online_client:
      sharing: {{ .enable_online_api }}
      # credentials_path: "{{ .config_dir }}/online_api_credentials.yaml"
    tls:
      agents_allowed_ou:
        {{- range .agents_allowed_ou }}
        - {{ . }}
        {{- end }}
      bouncers_allowed_ou:
        {{- range .bouncers_allowed_ou }}
        - {{ . }}
        {{- end }}
prometheus:
  enabled: {{ .enable_prometheus }}
{{ if .enable_prometheus }}
  level: full
  listen_addr: 0.0.0.0
  listen_port: 6060
{{ end }}
