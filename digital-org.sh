java -javaagent:elastic-apm-agent-1.18.0.jar \
     -Delastic.apm.service_name=digital-org \
     -Delastic.apm.server_urls=https://f1f0aa03fb074bfebdb92cc61de0c533.apm.eastus2.azure.elastic-cloud.com:443 \
     -Delastic.apm.secret_token=TCUYPytPX7EwpIMlK8 \
     -Delastic.apm.application_packages=com.engg.digitalorg \
     -jar digital-org-0.0.1-SNAPSHOT.jar
