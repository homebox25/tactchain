// Project: TactChain - Android Field Asset Tracker (CI/CD & SCM Showcase)
// Language: Kotlin + Markdown + Git (Backed by Bitbucket CI/CD)

// == README.md ==
# TactChain

**TactChain** is a lightweight Android application designed to simulate digital field asset tracking, integrating Git-based configuration management, Linux CI/CD pipelines, and optional high-performance packet processing (via DPDK or kernel tuning).

---

## Features
- Scan or input field asset IDs and track deployment status
- Local database using Room (SQLite)
- Push config data to Git repo (GitOps-style management)
- CI/CD pipeline using Bitbucket Pipelines to auto-build APKs
- Documentation using Confluence-style markdown
- Optional DPDK/Linux backend to simulate high-performance data ops

---

## Android App Structure (Kotlin)

```kotlin
@Entity
data class Asset(
    @PrimaryKey val assetId: String,
    val status: String,
    val lastUpdated: Long
)

@Dao
interface AssetDao {
    @Query("SELECT * FROM Asset")
    fun getAll(): List<Asset>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asset: Asset)
}

@Database(entities = [Asset::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
}
```

---

## CI/CD Pipeline Example (Bitbucket Pipelines)

```yaml
# bitbucket-pipelines.yml
image: androidsdk/android-30

pipelines:
  default:
    - step:
        name: Build APK
        caches:
          - gradle
        script:
          - ./gradlew assembleDebug
          - mkdir -p artifacts && cp app/build/outputs/apk/debug/app-debug.apk artifacts/
        artifacts:
          - artifacts/**
```

---

## Configuration Management (SCMP Snapshot)

```markdown
## Software Configuration Management Plan (SCMP)

### 1. Scope
This SCMP applies to the TactChain project, covering asset tracking software for edge/mobile deployments.

### 2. Configuration Identification
- Codebase: Git (Bitbucket)
- Baseline: v1.0-release branch
- Assets: Android APKs, YAML CI/CD files

### 3. Change Management
- All changes require Pull Request + SCCB review (simulated)
- Jira integration optional

### 4. Version Control
- GitFlow with develop, main, and feature branches

### 5. Status Accounting
- Bitbucket Pipelines generate build logs
- Confluence page stores audit records

### 6. Configuration Audits
- Baseline audit before milestone release
- CI builds + Git commit logs
```

---

## Optional: Backend DPDK/Kernel Simulation
If demonstrating low-latency comms:
- Deploy backend to Linux box or VM
- Use mock `netmap`, `dpdk-pktgen`, or even iperf3 stats
- Tie activity logs to Git repo

---

## Quick Setup
1. Clone repo and open in Android Studio
2. Run emulator or build APK
3. Scan or manually enter asset IDs
4. Push updates to Git to trigger build pipeline

---

