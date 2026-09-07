window.addEventListener("message", (event) => {
  if (event.source !== window || !event.data) {
    return;
  }
  if (event.data.type === "doocs-ai-ping") {
    window.postMessage({ type: "doocs-ai-pong", version: 1 }, "*");
    return;
  }
  if (event.data.type !== "doocs-ai-fetch") {
    return;
  }

  const id = event.data.id;
  const port = chrome.runtime.connect({ name: "doocs-ai" });
  let ok = false;
  let status = 0;

  port.onMessage.addListener((msg) => {
    if (msg.type === "meta") {
      ok = msg.ok;
      status = msg.status;
      return;
    }
    if (msg.type === "chunk") {
      window.postMessage(
        { type: "doocs-ai-chunk", id: id, text: msg.text || "" },
        "*"
      );
      return;
    }
    if (msg.type === "done") {
      window.postMessage(
        { type: "doocs-ai-done", id: id, ok: ok, status: status },
        "*"
      );
      port.disconnect();
      return;
    }
    if (msg.type === "error") {
      window.postMessage(
        { type: "doocs-ai-error", id: id, error: msg.error },
        "*"
      );
      port.disconnect();
    }
  });

  port.postMessage({
    type: "fetch",
    url: event.data.url,
    method: event.data.method,
    headers: event.data.headers,
    body: event.data.body,
  });
});
