chrome.runtime.onConnect.addListener((port) => {
  if (port.name !== "doocs-ai") {
    return;
  }
  port.onMessage.addListener(async (msg) => {
    if (!msg || msg.type !== "fetch") {
      return;
    }
    try {
      const res = await fetch(msg.url, {
        method: msg.method || "POST",
        headers: msg.headers || {},
        body: msg.body || undefined,
      });
      port.postMessage({ type: "meta", ok: res.ok, status: res.status });
      if (!res.body || !res.body.getReader) {
        port.postMessage({ type: "chunk", text: await res.text() });
        port.postMessage({ type: "done" });
        return;
      }
      const reader = res.body.getReader();
      const decoder = new TextDecoder();
      while (true) {
        const step = await reader.read();
        if (step.done) {
          break;
        }
        port.postMessage({
          type: "chunk",
          text: decoder.decode(step.value, { stream: true }),
        });
      }
      port.postMessage({ type: "done" });
    } catch (err) {
      port.postMessage({
        type: "error",
        error: String((err && err.message) || err),
      });
    }
  });
});
