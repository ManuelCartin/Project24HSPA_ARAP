# Upgrade Plan: ARAP Backend (20261005011808)

- **Generated**: 2026-10-04 19:18 -06:00
- **HEAD Branch**: main
- **HEAD Commit ID**: Not returned by version-control status

## Available Tools

**JDKs**
- JDK 17: not available (baseline will be skipped)
- JDK 25.0.1: `C:\Program Files\Java\jdk-25` (target runtime)

**Build Tools**
- Maven Wrapper 3.9.16: `demo/.mvn/wrapper/maven-wrapper.properties` (available; no upgrade needed)

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20261005011808
- Run tests before and after the upgrade: true
- Auto-execution: true

## Upgrade Goals

- Upgrade the Java runtime target from Java 17 to Java 25 (latest LTS).

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| --------------------- | ------- | ---------------------- | ---------------- |
| Java | 17 | 25 | User requested Java 25. |
| Spring Boot | 3.2.3 | 3.5.16 | The existing Spring Boot line predates Java 25 support; 3.5.16 is the newest 3.5 release available from Maven Central metadata and supports the requested runtime while avoiding a major framework migration. |
| Maven Wrapper | 3.9.16 | 3.9.16 | Current wrapper version is suitable for Java 25. |
| Spring Boot Maven plugin | Parent-managed | Parent-managed by Spring Boot 3.5.16 | Inherited plugin must remain aligned with the upgraded parent. |
| Spring Web, Spring Data MongoDB, DevTools, Spring Boot Test | Parent-managed | Parent-managed by Spring Boot 3.5.16 | Direct dependencies are BOM-managed and should remain aligned with the upgraded parent. |

## Derived Upgrades

- Upgrade Spring Boot to 3.5.16 so its managed framework and build plugins support Java 25.
- Keep the Maven Wrapper at 3.9.16; no wrapper change is required.
- No JDK 17 installation is required because it is only needed for the optional baseline run and is not installed.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|-----------|---------|--------|--------|--------|
| `arap-backend/demo/pom.xml` | `spring-boot-starter-parent` | 3.2.3 | upgrade | 3.5.16 | Required to use a Spring Boot line compatible with Java 25; also updates managed framework/plugin versions. |
| `arap-backend/demo/pom.xml` | `java.version` | 17 | upgrade | 25 | User-requested runtime target. |

### Source Code Changes

No source changes are expected. The inspected application code uses standard Java APIs and has no detected removed JDK APIs, internal JDK imports, or Java EE namespace references that block Java 25.

### Configuration Changes

No application configuration changes were identified as necessary for the Java runtime upgrade.

### CI/CD Changes

No CI/CD or container runtime files were found in the backend project scope.

### Risks & Warnings

- **MongoDB-dependent context test**: `ArapApplicationTests.contextLoads` starts the full Spring context and may need a reachable MongoDB instance. The baseline cannot be run because JDK 17 is not installed. **Mitigation**: Run the post-upgrade test suite on the installed JDK 25; if the context test fails, identify whether the cause is runtime compatibility or missing external MongoDB and fix or document only with concrete test evidence.
- **Pre-existing working-tree change**: `CircuitController.java` is modified before this upgrade. **Mitigation**: preserve it through branch preparation; do not edit or discard it as part of the Java upgrade.
- **Indirect dependency CVEs**: Spring Boot's managed dependency graph changes with the parent update. **Mitigation**: scan resolved direct dependencies after the upgrade and remediate any reported CVEs before final validation.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Confirm the already-installed Java 25 runtime and Maven Wrapper can run the planned upgrade.
  - **Changes to Make**: No installation is expected; use JDK 25 and the existing Maven Wrapper.
  - **Verification**: `java -version` and `.\mvnw.cmd -version`; Java 25 and Maven 3.9.16 are expected.
- Step 2: Setup Baseline
  - **Rationale**: A baseline helps distinguish pre-existing failures, but the configured Java 17 runtime is not installed.
  - **Changes to Make**: None; skip this optional baseline step because JDK 17 is unavailable.
  - **Verification**: Record baseline as skipped; do not install the obsolete base JDK solely for baseline testing.
- Step 3: Upgrade Java Runtime and Compatible Framework
  - **Rationale**: Set the requested compiler/runtime target and keep Spring Boot's framework and Maven plugin compatible with Java 25.
  - **Changes to Make**: Apply all Dependency Changes; preserve source behavior and the existing unrelated working-tree change. Scan resolved dependencies for CVEs and apply minimal patched-version updates if necessary.
  - **Verification**: `.\mvnw.cmd clean test-compile -q` and, where CVEs are fixed, repeat the compile and CVE scan on JDK 25.
- Step 4: Final Validation
  - **Rationale**: Confirm production and test compilation, runtime behavior, test results, and all requested versions after the upgrade.
  - **Changes to Make**: Resolve any failures caused by the upgrade and ensure the Java target and compatible Spring Boot version are retained.
  - **Verification**: On JDK 25 run `.\mvnw.cmd clean test-compile -q` and `.\mvnw.cmd clean test -q`; all compilation and tests are expected to pass.
