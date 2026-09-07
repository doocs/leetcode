# Doocs LeetCode AI plugin

Optional Chromium extension that lets the wiki Ask AI panel call OpenAI-compatible APIs without CORS.

1. Open `chrome://extensions` (or Edge `edge://extensions`).
2. Enable Developer mode.
3. Load unpacked and select this folder.
4. Reload [leetcode.doocs.org](https://leetcode.doocs.org). The panel status should say the plugin is connected.
5. In the panel settings, pick a provider and paste your own API key. The key stays in `localStorage` on the wiki origin; the extension only forwards the HTTPS request.
