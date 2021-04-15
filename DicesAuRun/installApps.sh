#! /bin/sh
cd /home/ubuntu/onos/bin
sudo ./onos-app localhost activate org.onosproject.proxyarp
sudo ./onos-app localhost activate org.onosproject.optical-model
sudo ./onos-app localhost activate org.onosproject.hostprovider
sudo ./onos-app localhost activate org.onosproject.lldpprovider
sudo ./onos-app localhost activate org.onosproject.openflow-base
sudo ./onos-app localhost activate org.onosproject.openflow
sudo ./onos-app localhost install /home/ubuntu/dices-app/dices_anonym/target/dices-app-1.0-SNAPSHOT.oar
sudo ./onos-app localhost install /home/ubuntu/dices-app-ECJ/target/dices-app-2.0-SNAPSHOT.oar