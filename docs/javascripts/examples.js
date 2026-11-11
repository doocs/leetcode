/* Copy button on each problem example block. */
(function (root) {
    function uiText(doc) {
        var lang = ((doc.documentElement && doc.documentElement.lang) || "").toLowerCase();
        if (lang.indexOf("zh") === 0) return { copy: "复制", copied: "已复制" };
        return { copy: "Copy", copied: "Copied" };
    }

    function blockText(block) {
        var clone = block.cloneNode(true);
        var button = clone.querySelector(".lc-example__copy");
        if (button) button.remove();
        var text = clone.innerText || clone.textContent || "";
        return text.replace(/\n+$/g, "").trim();
    }

    function copyIcon() {
        return '<svg viewBox="0 0 24 24" aria-hidden="true"><path d="M19 21H8V7h11m0-2H8a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h11a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2m-3-4H4a2 2 0 0 0-2 2v14h2V3h12z"/></svg>';
    }

    function checkIcon() {
        return '<svg viewBox="0 0 24 24" aria-hidden="true"><path d="M21 7 9 19l-5.5-5.5 1.41-1.41L9 16.17 19.59 5.59z"/></svg>';
    }

    function copyText(text) {
        if (navigator.clipboard && navigator.clipboard.writeText) {
            return navigator.clipboard.writeText(text).then(
                function () {
                    return true;
                },
                function () {
                    return false;
                }
            );
        }
        return Promise.resolve(false);
    }

    function enhance(doc) {
        var article = doc.querySelector("article.md-content__inner");
        if (!article || article.dataset.lcExamples) return;
        var nodes = article.querySelectorAll(":scope > pre, .example-block");
        if (!nodes.length) return;
        article.dataset.lcExamples = "1";
        var text = uiText(doc);
        for (var i = 0; i < nodes.length; i++) {
            var block = nodes[i];
            if (block.closest && block.closest(".lc-example")) continue;
            var host = doc.createElement("div");
            host.className = "lc-example";
            block.parentNode.insertBefore(host, block);
            host.appendChild(block);
            var button = doc.createElement("button");
            button.type = "button";
            button.className = "lc-example__copy";
            button.setAttribute("aria-label", text.copy);
            button.innerHTML = copyIcon();
            (function (target, btn) {
                btn.addEventListener("click", function () {
                    copyText(blockText(target)).then(function (ok) {
                        if (!ok) return;
                        btn.classList.add("is-copied");
                        btn.setAttribute("aria-label", text.copied);
                        btn.innerHTML = checkIcon();
                        setTimeout(function () {
                            btn.classList.remove("is-copied");
                            btn.setAttribute("aria-label", text.copy);
                            btn.innerHTML = copyIcon();
                        }, 1200);
                    });
                });
            })(block, button);
            host.appendChild(button);
        }
    }

    function boot() {
        enhance(document);
    }

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", boot);
    } else {
        boot();
    }
    if (root.document$ && root.document$.subscribe) {
        root.document$.subscribe(boot);
    }
})(typeof globalThis !== "undefined" ? globalThis : this);
