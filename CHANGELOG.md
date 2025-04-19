# Next release

- Upgraded dependency versions.
- Compiled against WireMock 3.12.1.

# 8.0.0

Date: 14 Dec 2023

- **BREAKING CHANGE**: Java 11 is minimum supported version now.
- `groupId` has changed to `io.buildlogic`.
- Compiled against WireMock 3.3.1.
- Upgraded dependency versions.

# 7.3.0

Date: 31 Dec 2022

- Compiled against WireMock 2.35.0.
- Upgraded dependency versions.

# 7.2.0

Date: 26 Jul 2022

- Compiled against WireMock 2.33.2.
- Upgraded dependency versions.
- Removed `xalan` from dependencies.

# 7.1.0

Date: 19 Mar 2022

- Compiled against WireMock 2.32.0.

# 7.0.0

Date: 23 Dec 2020

- **BREAKING CHANGE**: Added `wiremock` prefix to all properties.
- Compiled against WireMock 2.27.2.

# 6.0.0

Date: 17 Feb 2020

- **BREAKING CHANGE**: Java 7 is no longer supported. Java 8 is minimum supported version now.
- Added support for skipping plugin execution - resolves #92.
- Compiled against WireMock 2.26.0.

# 5.0.1

Date: 4 Dec 2019

- Removed explicit dependency management - fixes #85.
- Compiled against WireMock 2.25.1.

# 5.0.0

Date: 28 Sep 2019

- **BREAKING CHANGE**: Since this version, users are required to explicitly define which WireMock version they intend to use. See readme for more details.
- Compiled against WireMock 2.24.1.

# 4.6.0

Date: 7 Aug 2019

- Upgrade WireMock to 2.24.1.

# 4.5.0

Date: 18 Jul 2019

- Upgrade WireMock to 2.24.0.

# 4.4.1

Date: 18 May 2019

- Use WireMock 2.23.2.
- Workaround for #41 - projects using WireMock Maven Plugin won't fail if they define a dependency on an old version of Saxon library
  (`javax.xml.xpath.XPathFactory: Illegal configuration-file syntax`) - see [root cause](https://saxonica.plan.io/issues/1944).

# 4.4.0

Date: 15 Apr 2019

- Upgrade WireMock to 2.23.2.

# 4.3.0

Date: 1 Apr 2019

- Upgrade WireMock to 2.22.0.

# 4.2.0

Date: 1 Apr 2019

- Upgrade WireMock to 2.21.0.

# 4.1.0

Date: 31 Dec 2018

- Upgrade WireMock to 2.20.0.

# 4.0.0

Date: 30 Oct 2018

- Migrate back to Java.
- Maven 3.5.4 or later is no longer required - resolves #15.
- Use WireMock 2.19.0.

# 3.0.0

Date: 3 Oct 2018

- Migrate to Scala.
- Upgrade WireMock to 2.19.0.
- Upgrade all dependencies to most recent versions.

# 2.14.0

Date: 6 Aug 2018

- Explicitly stop WireMock in `post-integration-test` phase with newly introduced stop goal. See #10. See readme for detailed info.

# 2.13.1

Date: 6 Aug 2018

- Amend log level for classpath elements (see #12).

# 2.13.0

Date: 2 Jul 2018

- Upgrade WireMock to 2.18.0.

# 2.12.0

Date: 30 Mar 2018

- `groupId` has changed to `uk.co.automatictester`.
- Upgrade WireMock to 2.15.0.

# 2.11.0

Date: 24 Jan 2018

- Upgrade WireMock to 2.14.0.

# 2.10.0

Date: 24 Jan 2018

- Upgrade WireMock to 2.13.0.

# 2.9.0

Date: 28 Dec 2017

- Upgrade WireMock to 2.12.0.

# 2.8.0

Date: 28 Dec 2017

- Upgrade WireMock to 2.11.0.

# 2.7.0

Date: 1 Nov 2017

- Based on WireMock 2.10.1.

# 2.6.1

Date: 20 Oct 2017

- Based on WireMock 2.9.0.
- Fixed issue #7: Can't handle root directory with space in path.

# 2.6.0

Date: 17 Oct 2017

- Upgrade WireMock to 2.9.0.

# 2.5.0

Date: 12 Oct 2017

- Based on WireMock 2.8.0.
- Introduced optional `keepRunning` configuration parameter which comes handy when `wiremock:run` is started outside of standard Maven lifecycle. See issue #6
  and project readme for more information.

# 2.4.0

Date: 8 Oct 2017

- Upgrade WireMock to 2.8.0.

# 2.3.0

Date: 3 May 2017

- Upgrade WireMock to 2.6.0.
- Added support for WireMock extensions, see PR #5 - thanks @tjisse!

# 2.2.0

Date: 6 Jan 2017

- Upgrade WireMock to 2.5.0.

# 2.1.0

Date: 18 Dec 2016

- `groupId` has changed to `uk.co.deliverymind`.
- Upgrade WireMock to 2.4.1.

# 2.0.0

Date: 10 Sep 2016

# 1.0.0

Date: 7 Sep 2016
