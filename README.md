# Surah Cube — 1000 Points 🕋

A browser + Android game built around a 10×10×10 cube of **1000 points**,
tying each hour of the day to surahs of the Quran.

![app icon](SurahCubeApp/play-store-icon.png)

## How it works

- The cube holds points **1–1000**: points **1–100 form the base layer**,
  **901–1000 the top layer**.
- Every hour, different points need to be *taken care of*:
  - **1 o'clock → surahs 1–9**, **2 o'clock → surahs 10–18**, … **12 o'clock → surahs 100–108**
  - **Surahs 109–114 are wildcards** — due at *any* hour
- A point is "due" when it is a **multiple** of an active surah
  (e.g. at 8 o'clock surah 67 lights up points 67, 134, 201, 268, … up to 1000).
- **Click** glowing points to take care of them (+10, +100 bonus for clearing the hour).
- Hovering any point shows **point # mod 114** and its cube root.
- **🎲 Random point** button picks a point for you.

## 🕌 Fajr alarm clock + 1000-cities square

- Each point **#1–1000 is also one of the world's 1000 largest cities**
  (GeoNames data, ranked by population) — the "1000 cities" number square.
- **🏙️ City mode**: hover/click any point to see its city; click to jump to
  that city's Fajr time and set the alarm.
- **🕌 Fajr alarm**: uses your device location (or any of the 1000 cities),
  shows a live countdown, and rings a chime at Fajr.
- Times use the **Muslim World League 18°** method — via
  [Aladhan API](https://aladhan.com/prayer-times-api) when online (cached per
  day), with a built-in astronomical calculation (PrayTimes.org algorithm) as
  offline fallback. Approximate ±2 min — always confirm with your local masjid.

## Built-in Quran reader 📖

Click any point and choose:

- **Read Surah (n mod 114)** — e.g. point 268 → Surah 40 (Ghafir)
- **Read Surah (∛n cube root)** — e.g. ∛268 ≈ 6.45 → Surah 6 (Al-An'am)

The reader shows each ayah in **Arabic, English and Urdu**, with:

- **Auto-scroll calibrated to a 17 min/juz pace** (whole-Quran ≈ 8.5 h),
  adjustable 0.25×–4×
- Font-size slider
- Hand-drawn scene backgrounds: 🌲 Forest · 🏜️ Desert · ⛰️ Mountains · Plain
- Manual scrolling pauses auto-scroll; progress bar at the bottom

Surah text comes from [api.alquran.cloud](https://alquran.cloud/api)
(`quran-uthmani`, `en.asad`, `ur.jalandhry`) and is cached offline after first read.

## Run it

**Browser:** just open [`surah-cube-game.html`](surah-cube-game.html) — a single
self-contained file, no dependencies.

**Android:** download [`SurahCube-release.apk`](SurahCube-release.apk) and install
(allow "install from unknown sources"). First surah load needs internet; then cached.

## Build the APK yourself

See [SurahCubeApp/README.md](SurahCubeApp/README.md) — a minimal WebView wrapper
(Android Studio / Gradle). Signing keys are **not** included; generate your own
keystore + `keystore.properties` (see that README) to build a signed release.

## License

MIT — see below. Quran text served by the AlQuran Cloud API; translations belong
to their respective authors.
