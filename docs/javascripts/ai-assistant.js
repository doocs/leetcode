(function () {
    var SCRIPT = document.getElementById("doocs-ai-config");
    if (!SCRIPT) {
        return;
    }

    var CONFIG;
    try {
        CONFIG = JSON.parse(SCRIPT.textContent || "{}");
    } catch (err) {
        return;
    }

    var STORAGE = "doocs-ai.settings";
    var ZH = CONFIG.lang !== "en";
    var I18N = ZH
        ? {
              title: "问 AI",
              ask: "问 AI",
              settings: "设置",
              close: "关闭",
              send: "发送",
              stop: "停止",
              save: "保存",
              placeholder: "问这道题，或解释当前代码…",
              mine: "可选：粘贴你的写法，用来对比",
              starterExplain: "这题在考什么？",
              starterWalk: "逐步讲解当前语言题解",
              starterHint: "先给思路，不要直接贴完整代码",
              starterCompare: "对比我粘贴的写法",
              provider: "提供商",
              model: "模型",
              key: "API Key（只存在本机浏览器）",
              base: "接口地址",
              pluginOn: "浏览器插件已连接",
              pluginOff: "未检测到插件，直连可能被 CORS 拦截",
              pluginHelp: "多数模型 API 不允许网页直连。请加载仓库 extensions/doocs-ai 插件，或把接口地址改成你自己的 OpenAI 兼容代理。",
              needKey: "先在设置里填 API Key",
              empty: "输入问题，或点上面的快捷提问",
              error: "请求失败",
              cors: "浏览器拦截了跨域请求。安装 extensions/doocs-ai 插件，或把接口改成你自己的代理",
              thinking: "生成中…",
          }
        : {
              title: "Ask AI",
              ask: "Ask AI",
              settings: "Settings",
              close: "Close",
              send: "Send",
              stop: "Stop",
              save: "Save",
              placeholder: "Ask about this problem or the current solution…",
              mine: "Optional: paste your code to compare",
              starterExplain: "What is this problem testing?",
              starterWalk: "Walk through the current language solution",
              starterHint: "Give hints first, no full code yet",
              starterCompare: "Compare with the code I pasted",
              provider: "Provider",
              model: "Model",
              key: "API key (stored only in this browser)",
              base: "Base URL",
              pluginOn: "Browser plugin connected",
              pluginOff: "No plugin detected; direct calls may be blocked by CORS",
              pluginHelp: "Most model APIs block browser CORS. Load extensions/doocs-ai, or point the base URL at your own OpenAI-compatible proxy.",
              needKey: "Add an API key in Settings first",
              empty: "Ask a question, or use a starter prompt",
              error: "Request failed",
              cors: "The browser blocked a cross-origin request. Install extensions/doocs-ai, or point the base URL at your proxy",
              thinking: "Thinking…",
          };

    var state = {
        open: false,
        settings: false,
        plugin: false,
        abort: null,
        messages: [],
    };

    var els = {};

    function providers() {
        return CONFIG.providers && CONFIG.providers.length
            ? CONFIG.providers
            : [];
    }

    function loadSettings() {
        var raw;
        try {
            raw = JSON.parse(localStorage.getItem(STORAGE) || "{}");
        } catch (err) {
            raw = {};
        }
        var list = providers();
        var first = list[0] || {};
        var provider = raw.provider || CONFIG.defaultProvider || first.id || "custom";
        var found = null;
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === provider) {
                found = list[i];
                break;
            }
        }
        if (!found) {
            found = first;
            provider = found.id || "custom";
        }
        return {
            provider: provider,
            model: raw.model || (found.models && found.models[0]) || "",
            apiKey: raw.apiKey || "",
            baseUrl: raw.baseUrl || found.base_url || "",
        };
    }

    function saveSettings(next) {
        localStorage.setItem(STORAGE, JSON.stringify(next));
    }

    function findProvider(id) {
        var list = providers();
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return list[i];
            }
        }
        return { id: "custom", name: "Custom", base_url: "", models: [] };
    }

    function pageTitle() {
        var h1 = document.querySelector(".md-content h1");
        return (
            CONFIG.title ||
            (h1 && h1.textContent.trim()) ||
            document.title ||
            ""
        );
    }

    function activeLang() {
        var inputs = document.querySelectorAll(".tabbed-set > input");
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].checked) {
                var label = document.querySelector(
                    'label[for="' + inputs[i].id + '"]'
                );
                return label ? label.textContent.trim() : "";
            }
        }
        return "";
    }

    function activeCode(limit) {
        var max = limit || 8000;
        var sets = document.querySelectorAll(".tabbed-set");
        for (var s = 0; s < sets.length; s++) {
            var inputs = sets[s].querySelectorAll(":scope > input");
            var idx = -1;
            for (var i = 0; i < inputs.length; i++) {
                if (inputs[i].checked) {
                    idx = i;
                    break;
                }
            }
            var blocks = sets[s].querySelectorAll(
                ".tabbed-content > .tabbed-block"
            );
            var block = idx >= 0 ? blocks[idx] : blocks[0];
            if (block) {
                var code = block.querySelector("pre code") || block.querySelector("code");
                if (code && code.textContent.trim()) {
                    return code.textContent.trim().slice(0, max);
                }
            }
        }
        var fallback = document.querySelector(".md-content pre code");
        return fallback ? fallback.textContent.trim().slice(0, max) : "";
    }

    function pageExcerpt() {
        var article = document.querySelector(".md-content__inner");
        if (!article) {
            return "";
        }
        var clone = article.cloneNode(true);
        var drop = clone.querySelectorAll(
            "pre, script, style, .doocs-ai-panel, .md-source-file, .md-feedback"
        );
        for (var i = 0; i < drop.length; i++) {
            drop[i].parentNode.removeChild(drop[i]);
        }
        return (clone.textContent || "").replace(/\s+/g, " ").trim().slice(0, 5000);
    }

    function systemPrompt() {
        var langLine = ZH
            ? "用简洁的中文回答。解释本站已经写好的题解，不要另编一套题解，也不要编造不存在的题号。"
            : "Answer in concise English. Explain the solution already on this page. Do not invent a new solution or problem numbers.";
        return [
            "You are the optional AI helper for Doocs LeetCode Wiki.",
            langLine,
            "Ground every claim in the provided page context. If something is missing, say so.",
            "Prefer hints and walkthroughs of the existing code over dumping a replacement.",
            "When you cite, point at the current page title or a language tab.",
            "Page title: " + pageTitle(),
            "Page URL: " + location.href,
            "Active language tab: " + (activeLang() || "(none)"),
        ].join("\n");
    }

    function contextMessage(selection, mine) {
        var parts = [
            "Page excerpt:\n" + (pageExcerpt() || "(empty)"),
            "Current solution (" + (activeLang() || "code") + "):\n" + (activeCode() || "(none)"),
        ];
        if (selection) {
            parts.push("User selection:\n" + selection);
        }
        if (mine) {
            parts.push("User's own code:\n" + mine);
        }
        return parts.join("\n\n");
    }

    function escapeHtml(text) {
        return String(text)
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;");
    }

    function renderMd(text) {
        var escaped = escapeHtml(text);
        escaped = escaped.replace(/```[\w-]*\n([\s\S]*?)```/g, function (_, code) {
            return '<pre class="doocs-ai-code"><code>' + code + "</code></pre>";
        });
        escaped = escaped.replace(/`([^`]+)`/g, "<code>$1</code>");
        escaped = escaped.replace(/\*\*(.+?)\*\*/g, "<strong>$1</strong>");
        escaped = escaped.replace(
            /\[([^\]]+)\]\((https?:[^)]+)\)/g,
            '<a href="$2" rel="noopener noreferrer" target="_blank">$1</a>'
        );
        return escaped.replace(/\n/g, "<br>");
    }

    function setStatus() {
        if (!els.status) {
            return;
        }
        els.status.dataset.ok = state.plugin ? "true" : "false";
        els.status.textContent = state.plugin ? I18N.pluginOn : I18N.pluginOff;
    }

    function pingPlugin() {
        return new Promise(function (resolve) {
            var done = false;
            function onMsg(ev) {
                if (ev.source !== window || !ev.data || ev.data.type !== "doocs-ai-pong") {
                    return;
                }
                done = true;
                window.removeEventListener("message", onMsg);
                resolve(true);
            }
            window.addEventListener("message", onMsg);
            window.postMessage({ type: "doocs-ai-ping" }, "*");
            setTimeout(function () {
                if (!done) {
                    window.removeEventListener("message", onMsg);
                    resolve(false);
                }
            }, 250);
        });
    }

    function pluginFetch(url, options, onChunk) {
        return new Promise(function (resolve, reject) {
            var id = "r" + Date.now() + Math.random().toString(16).slice(2);
            var body = "";
            function onMsg(ev) {
                if (ev.source !== window || !ev.data || ev.data.id !== id) {
                    return;
                }
                var data = ev.data;
                if (data.type === "doocs-ai-chunk") {
                    body += data.text || "";
                    if (onChunk && data.text) {
                        onChunk(data.text);
                    }
                } else if (data.type === "doocs-ai-done") {
                    window.removeEventListener("message", onMsg);
                    resolve({ ok: data.ok, status: data.status, body: body });
                } else if (data.type === "doocs-ai-error") {
                    window.removeEventListener("message", onMsg);
                    reject(new Error(data.error || I18N.error));
                }
            }
            window.addEventListener("message", onMsg);
            window.postMessage(
                {
                    type: "doocs-ai-fetch",
                    id: id,
                    url: url,
                    method: options.method || "POST",
                    headers: options.headers || {},
                    body: options.body || "",
                },
                "*"
            );
        });
    }

    function parseSseDelta(buffer, onDelta) {
        var parts = buffer.split("\n\n");
        var rest = parts.pop();
        for (var i = 0; i < parts.length; i++) {
            var line = parts[i].replace(/^data:\s*/, "").trim();
            if (!line || line === "[DONE]") {
                continue;
            }
            try {
                var json = JSON.parse(line);
                var delta =
                    json.choices &&
                    json.choices[0] &&
                    json.choices[0].delta &&
                    json.choices[0].delta.content;
                if (delta) {
                    onDelta(delta);
                }
            } catch (err) {
                /* ignore partial JSON */
            }
        }
        return rest;
    }

    async function directFetch(url, options, onChunk, signal) {
        var res = await fetch(url, {
            method: options.method || "POST",
            headers: options.headers,
            body: options.body,
            signal: signal,
        });
        if (!res.ok) {
            var errText = await res.text();
            throw new Error((errText || res.statusText || I18N.error).slice(0, 400));
        }
        if (!res.body || !res.body.getReader) {
            var full = await res.text();
            onChunk(full);
            return;
        }
        var reader = res.body.getReader();
        var decoder = new TextDecoder();
        var buf = "";
        while (true) {
            var step = await reader.read();
            if (step.done) {
                break;
            }
            buf += decoder.decode(step.value, { stream: true });
            buf = parseSseDelta(buf, onChunk);
        }
        if (buf) {
            parseSseDelta(buf + "\n\n", onChunk);
        }
    }

    async function chat(userText, extras) {
        var settings = loadSettings();
        if (!settings.apiKey && settings.provider !== "ollama") {
            throw new Error(I18N.needKey);
        }
        var base = (settings.baseUrl || "").replace(/\/$/, "");
        if (!base) {
            throw new Error(I18N.base);
        }
        var url = base + "/chat/completions";
        var payload = {
            model: settings.model,
            stream: true,
            temperature: 0.3,
            messages: [
                { role: "system", content: systemPrompt() },
                { role: "user", content: contextMessage(extras.selection, extras.mine) },
                { role: "user", content: userText },
            ],
        };
        var headers = { "Content-Type": "application/json" };
        if (settings.apiKey) {
            headers.Authorization = "Bearer " + settings.apiKey;
        }
        var options = {
            method: "POST",
            headers: headers,
            body: JSON.stringify(payload),
        };
        var assembled = "";
        function onText(chunk) {
            assembled += chunk;
            extras.onDelta(assembled);
        }
        if (state.plugin) {
            var sseBuf = "";
            var result = await pluginFetch(url, options, function (chunk) {
                sseBuf += chunk;
                sseBuf = parseSseDelta(sseBuf, function (delta) {
                    assembled += delta;
                    extras.onDelta(assembled);
                });
            });
            if (!result.ok) {
                throw new Error((result.body || I18N.error).slice(0, 400));
            }
            if (sseBuf) {
                parseSseDelta(sseBuf + "\n\n", function (delta) {
                    assembled += delta;
                    extras.onDelta(assembled);
                });
            }
            return assembled;
        }
        try {
            await directFetch(url, options, function (delta) {
                onText(delta);
            }, extras.signal);
        } catch (err) {
            var msg = String((err && err.message) || err);
            if (/Failed to fetch|NetworkError|CORS/i.test(msg)) {
                throw new Error(I18N.cors);
            }
            throw err;
        }
        return assembled;
    }

    function addMsg(role, html) {
        var div = document.createElement("div");
        div.className = "doocs-ai-msg";
        div.dataset.role = role;
        div.innerHTML = html;
        els.body.appendChild(div);
        els.body.scrollTop = els.body.scrollHeight;
        return div;
    }

    function setOpen(open) {
        state.open = open;
        els.panel.dataset.open = open ? "true" : "false";
        els.toggle.dataset.open = open ? "true" : "false";
        els.panel.setAttribute("aria-hidden", open ? "false" : "true");
        if (open) {
            els.input.focus();
        }
    }

    function setSettings(open) {
        state.settings = open;
        els.settings.dataset.open = open ? "true" : "false";
        els.body.style.display = open ? "none" : "block";
        els.foot.style.display = open ? "none" : "flex";
        if (open) {
            fillSettings();
        }
    }

    function fillSettings() {
        var settings = loadSettings();
        var list = providers();
        els.provider.innerHTML = "";
        for (var i = 0; i < list.length; i++) {
            var opt = document.createElement("option");
            opt.value = list[i].id;
            opt.textContent = list[i].name;
            if (list[i].id === settings.provider) {
                opt.selected = true;
            }
            els.provider.appendChild(opt);
        }
        fillModels(settings.provider, settings.model);
        els.apiKey.value = settings.apiKey;
        els.baseUrl.value = settings.baseUrl;
    }

    function fillModels(providerId, selected) {
        var provider = findProvider(providerId);
        els.model.innerHTML = "";
        var models = provider.models && provider.models.length ? provider.models : [""];
        for (var i = 0; i < models.length; i++) {
            var opt = document.createElement("option");
            opt.value = models[i];
            opt.textContent = models[i] || (ZH ? "自定义" : "Custom");
            if (models[i] === selected) {
                opt.selected = true;
            }
            els.model.appendChild(opt);
        }
        if (selected && models.indexOf(selected) < 0) {
            var extra = document.createElement("option");
            extra.value = selected;
            extra.textContent = selected;
            extra.selected = true;
            els.model.appendChild(extra);
        }
    }

    function persistFromForm() {
        var provider = findProvider(els.provider.value);
        saveSettings({
            provider: els.provider.value,
            model: els.model.value,
            apiKey: els.apiKey.value.trim(),
            baseUrl: els.baseUrl.value.trim() || provider.base_url || "",
        });
    }

    async function submit(text, selection) {
        var query = (text || "").trim();
        if (!query) {
            return;
        }
        if (state.abort) {
            state.abort.abort();
        }
        setSettings(false);
        addMsg("user", escapeHtml(query).replace(/\n/g, "<br>"));
        var bubble = addMsg("assistant", I18N.thinking);
        var controller = new AbortController();
        state.abort = controller;
        els.send.textContent = I18N.stop;
        try {
            var answer = await chat(query, {
                selection: selection || "",
                mine: (els.mine.value || "").trim(),
                signal: controller.signal,
                onDelta: function (full) {
                    bubble.innerHTML = renderMd(full);
                    els.body.scrollTop = els.body.scrollHeight;
                },
            });
            bubble.innerHTML = renderMd(answer || I18N.empty);
        } catch (err) {
            if (err && err.name === "AbortError") {
                bubble.innerHTML = renderMd(bubble.textContent || I18N.empty);
            } else {
                bubble.textContent = I18N.error + ": " + ((err && err.message) || err);
            }
        }
        state.abort = null;
        els.send.textContent = I18N.send;
    }

    function svg(path) {
        return (
            '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="18" height="18">' +
            '<path fill="currentColor" d="' +
            path +
            '"></path></svg>'
        );
    }

    function mount() {
        var toggle = document.createElement("button");
        toggle.className = "md-header__button md-icon doocs-ai-toggle";
        toggle.type = "button";
        toggle.title = I18N.title;
        toggle.setAttribute("aria-label", I18N.title);
        toggle.innerHTML = svg(
            "M12 2l1.4 4.2L18 7.6l-3.3 3 0.8 4.4L12 13.2 8.5 15l0.8-4.4L6 7.6l4.6-1.4L12 2m0 8.2l1.2 2.4 2.6.4-1.9 1.8.4 2.6L12 13.8 9.7 15.4l.4-2.6-1.9-1.8 2.6-.4L12 10.2z"
        );

        var header = document.querySelector(".md-header__inner");
        if (header) {
            header.appendChild(toggle);
        } else {
            document.body.appendChild(toggle);
        }

        var panel = document.createElement("aside");
        panel.className = "doocs-ai-panel";
        panel.dataset.open = "false";
        panel.setAttribute("aria-hidden", "true");
        panel.innerHTML =
            '<div class="doocs-ai-head">' +
            "<h2>" +
            I18N.title +
            "</h2>" +
            '<button type="button" class="doocs-ai-icon" data-act="settings" title="' +
            I18N.settings +
            '">' +
            svg("M12 8a4 4 0 1 1 0 8 4 4 0 0 1 0-8m8.9 4a7.9 7.9 0 0 0-.2-1.6l2.1-1.6-2-3.4-2.5 1a8 8 0 0 0-2.8-1.6L15 2h-6l-.5 2.8a8 8 0 0 0-2.8 1.6l-2.5-1-2 3.4 2.1 1.6A8 8 0 0 0 3.1 12c0 .5.1 1.1.2 1.6L1.2 15.2l2 3.4 2.5-1a8 8 0 0 0 2.8 1.6L9 22h6l.5-2.8a8 8 0 0 0 2.8-1.6l2.5 1 2-3.4-2.1-1.6c.1-.5.2-1.1.2-1.6z") +
            "</button>" +
            '<button type="button" class="doocs-ai-icon" data-act="close" title="' +
            I18N.close +
            '">' +
            svg("M19 6.4L17.6 5 12 10.6 6.4 5 5 6.4 10.6 12 5 17.6 6.4 19 12 13.4 17.6 19 19 17.6 13.4 12 19 6.4z") +
            "</button>" +
            "</div>" +
            '<div class="doocs-ai-status"></div>' +
            '<div class="doocs-ai-body"></div>' +
            '<div class="doocs-ai-settings">' +
            "<label>" +
            I18N.provider +
            '</label><select data-field="provider"></select>' +
            "<label>" +
            I18N.model +
            '</label><select data-field="model"></select>' +
            "<label>" +
            I18N.base +
            '</label><input data-field="base" spellcheck="false">' +
            "<label>" +
            I18N.key +
            '</label><input data-field="key" type="password" autocomplete="off" spellcheck="false">' +
            '<button type="button" class="doocs-ai-save" data-act="save">' +
            I18N.save +
            "</button>" +
            '<p class="doocs-ai-help">' +
            I18N.pluginHelp +
            "</p>" +
            "</div>" +
            '<div class="doocs-ai-foot">' +
            '<div class="doocs-ai-starters">' +
            '<button type="button" data-q="explain">' +
            I18N.starterExplain +
            "</button>" +
            '<button type="button" data-q="walk">' +
            I18N.starterWalk +
            "</button>" +
            '<button type="button" data-q="hint">' +
            I18N.starterHint +
            "</button>" +
            '<button type="button" data-q="compare">' +
            I18N.starterCompare +
            "</button>" +
            "</div>" +
            '<textarea class="doocs-ai-mine" placeholder="' +
            I18N.mine +
            '"></textarea>' +
            '<div class="doocs-ai-composer">' +
            '<textarea data-field="input" placeholder="' +
            I18N.placeholder +
            '"></textarea>' +
            '<button type="button" class="doocs-ai-send" data-act="send">' +
            I18N.send +
            "</button>" +
            "</div></div>";

        document.body.appendChild(panel);

        var ask = document.createElement("button");
        ask.type = "button";
        ask.className = "doocs-ai-ask";
        ask.textContent = I18N.ask;
        document.body.appendChild(ask);

        els.toggle = toggle;
        els.panel = panel;
        els.status = panel.querySelector(".doocs-ai-status");
        els.body = panel.querySelector(".doocs-ai-body");
        els.settings = panel.querySelector(".doocs-ai-settings");
        els.foot = panel.querySelector(".doocs-ai-foot");
        els.provider = panel.querySelector('[data-field="provider"]');
        els.model = panel.querySelector('[data-field="model"]');
        els.baseUrl = panel.querySelector('[data-field="base"]');
        els.apiKey = panel.querySelector('[data-field="key"]');
        els.input = panel.querySelector('[data-field="input"]');
        els.mine = panel.querySelector(".doocs-ai-mine");
        els.send = panel.querySelector('[data-act="send"]');
        els.ask = ask;

        addMsg("assistant", I18N.empty);
        setStatus();

        toggle.addEventListener("click", function () {
            setOpen(!state.open);
        });
        panel.querySelector('[data-act="close"]').addEventListener("click", function () {
            setOpen(false);
        });
        panel.querySelector('[data-act="settings"]').addEventListener("click", function () {
            setSettings(!state.settings);
        });
        panel.querySelector('[data-act="save"]').addEventListener("click", function () {
            persistFromForm();
            setSettings(false);
        });
        els.provider.addEventListener("change", function () {
            var provider = findProvider(els.provider.value);
            els.baseUrl.value = provider.base_url || "";
            fillModels(provider.id, provider.models && provider.models[0]);
        });
        els.send.addEventListener("click", function () {
            if (state.abort) {
                state.abort.abort();
                return;
            }
            submit(els.input.value);
            els.input.value = "";
        });
        els.input.addEventListener("keydown", function (ev) {
            if (ev.key === "Enter" && !ev.shiftKey) {
                ev.preventDefault();
                submit(els.input.value);
                els.input.value = "";
            }
        });
        panel.querySelectorAll("[data-q]").forEach(function (btn) {
            btn.addEventListener("click", function () {
                var map = {
                    explain: I18N.starterExplain,
                    walk: I18N.starterWalk,
                    hint: I18N.starterHint,
                    compare: I18N.starterCompare,
                };
                submit(map[btn.getAttribute("data-q")] || btn.textContent);
            });
        });
        ask.addEventListener("click", function () {
            var selected = (window.getSelection() && window.getSelection().toString()) || "";
            ask.style.display = "none";
            setOpen(true);
            submit(I18N.starterExplain, selected);
        });

        document.addEventListener("keydown", function (ev) {
            if ((ev.ctrlKey || ev.metaKey) && ev.key.toLowerCase() === "i") {
                ev.preventDefault();
                setOpen(!state.open);
            }
            if (ev.key === "Escape" && state.open) {
                setOpen(false);
            }
        });
        document.addEventListener("mouseup", function () {
            var sel = window.getSelection();
            var text = sel && String(sel).trim();
            if (!text || text.length < 2) {
                ask.style.display = "none";
                return;
            }
            try {
                var rect = sel.getRangeAt(0).getBoundingClientRect();
                ask.style.display = "block";
                ask.style.top = window.scrollY + rect.bottom + 8 + "px";
                ask.style.left = window.scrollX + rect.left + "px";
            } catch (err) {
                ask.style.display = "none";
            }
        });

        bindCodeButtons();
        if (window.document$ && document$.subscribe) {
            document$.subscribe(function () {
                bindCodeButtons();
            });
        }
    }

    function bindCodeButtons() {
        var blocks = document.querySelectorAll(".md-content .highlight, .md-content pre");
        for (var i = 0; i < blocks.length; i++) {
            var host = blocks[i];
            if (host.tagName === "PRE" && host.closest(".highlight")) {
                continue;
            }
            if (host.querySelector(".doocs-ai-codebtn")) {
                continue;
            }
            var btn = document.createElement("button");
            btn.type = "button";
            btn.className = "doocs-ai-codebtn";
            btn.textContent = I18N.ask;
            btn.addEventListener("click", function (ev) {
                ev.preventDefault();
                var host = ev.currentTarget.parentNode;
                var code = host.querySelector("code");
                setOpen(true);
                submit(I18N.starterWalk, code ? code.textContent.trim().slice(0, 8000) : "");
            });
            host.insertBefore(btn, host.firstChild);
        }
    }

    async function boot() {
        mount();
        state.plugin = await pingPlugin();
        setStatus();
        if (els.settings) {
            var help = els.settings.querySelector(".doocs-ai-help");
            if (help) {
                help.textContent = state.plugin ? I18N.pluginOn : I18N.pluginHelp;
            }
        }
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", boot);
    } else {
        boot();
    }
})();
