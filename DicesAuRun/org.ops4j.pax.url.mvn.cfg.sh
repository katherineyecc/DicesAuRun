################################################################################
#
#    Licensed to the Apache Software Foundation (ASF) under one or more
#    contributor license agreements.  See the NOTICE file distributed with
#    this work for additional information regarding copyright ownership.
#    The ASF licenses this file to You under the Apache License, Version 2.0
#    (the "License"); you may not use this file except in compliance with
#    the License.  You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#
################################################################################

#
# If set to true, the following property will not allow any certificate to be used
# when accessing Maven repositories through SSL
#
#org.ops4j.pax.url.mvn.certificateCheck=

#
# Path to the local Maven settings file.
# The repositories defined in this file will be automatically added to the list
# of default repositories if the 'org.ops4j.pax.url.mvn.repositories' property
# below is not set.
# The following locations are checked for the existence of the settings.xml file
#   * 1. looks for the specified url
#   * 2. if not found looks for ${user.home}/.m2/settings.xml
#   * 3. if not found looks for ${maven.home}/conf/settings.xml
#   * 4. if not found looks for ${M2_HOME}/conf/settings.xml
#
#org.ops4j.pax.url.mvn.settings=

#
# Path to the local Maven repository which is used to avoid downloading
# artifacts when they already exist locally.
# The value of this property will be extracted from the settings.xml file
# above, or defaulted to:
#     System.getProperty( "user.home" ) + "/.m2/repository"
#
org.ops4j.pax.url.mvn.localRepository=/home/ubuntu/.m2/repository

#
# Default this to false. It's just weird to use undocumented repos
#
org.ops4j.pax.url.mvn.useFallbackRepositories=false

#
# Uncomment if you don't wanna use the proxy settings
# from the Maven conf/settings.xml file
#
# org.ops4j.pax.url.mvn.proxySupport=false

#
# Comma separated list of repositories scanned when resolving an artifact.
# Those repositories will be checked before iterating through the
#    below list of repositories and even before the local repository
# A repository url can be appended with zero or more of the following flags:
#    @snapshots  : the repository contains snaphots
#    @noreleases : the repository does not contain any released artifacts
#
# The following property value will add the system folder as a repo.
#
org.ops4j.pax.url.mvn.defaultRepositories=\
    file:${karaf.home}/${karaf.default.repository}@id=system.repository@snapshots,\
    file:${karaf.data}/kar@id=kar.repository@multi@snapshots

# Use the default local repo (e.g.~/.m2/repository) as a "remote" repo
#org.ops4j.pax.url.mvn.defaultLocalRepoAsRemote=false

#
# Comma separated list of repositories scanned when resolving an artifact.
# The default list includes the following repositories:
#    http://repo1.maven.org/maven2@id=central
#    http://repository.springsource.com/maven/bundles/release@id=spring.ebr
#    http://repository.springsource.com/maven/bundles/external@id=spring.ebr.external
#    http://zodiac.springsource.com/maven/bundles/release@id=gemini
#    http://repository.apache.org/content/groups/snapshots-group@id=apache@snapshots@noreleases
#    https://oss.sonatype.org/content/repositories/snapshots@id=sonatype.snapshots.deploy@snapshots@noreleases
#    https://oss.sonatype.org/content/repositories/ops4j-snapshots@id=ops4j.sonatype.snapshots.deploy@snapshots@noreleases
# To add repositories to the default ones, prepend '+' to the list of repositories
# to add.
# A repository url can be appended with zero or more of the following flags:
#    @snapshots  : the repository contains snapshots
#    @noreleases : the repository does not contain any released artifacts
#    @id=repository.id : the id for the repository, just like in the settings.xml this is optional but recommended
#
org.ops4j.pax.url.mvn.repositories= \
    ${org.ops4j.pax.url.mvn.defaultRepositories}, \
    http://repo1.maven.org/maven2@id=central, \
    http://repository.springsource.com/maven/bundles/release@id=spring.ebr.release, \
    http://repository.springsource.com/maven/bundles/external@id=spring.ebr.external, \
    http://zodiac.springsource.com/maven/bundles/release@id=gemini, \
    http://repository.apache.org/content/groups/snapshots-group@id=apache@snapshots@noreleases, \
    https://oss.sonatype.org/content/repositories/snapshots@id=sonatype.snapshots.deploy@snapshots@noreleases, \
    https://oss.sonatype.org/content/repositories/ops4j-snapshots@id=ops4j.sonatype.snapshots.deploy@snapshots@noreleases
