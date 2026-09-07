const ALLOW_HOSTS = [
  "leetcode.doocs.org",
  "doocs.gitee.io",
  "127.0.0.1",
  "localhost",
];

function originAllowed(origin) {
  if (!origin) {
    return false;
  }
  try {
    const host = new URL(origin).hostname;
    return ALLOW_HOSTS.some((item) => host === item || host.endsWith(`.${item}`));
  } catch {
    return false;
  }
}

function corsHeaders(request) {
  const origin = request.headers.get("Origin") || "";
  const headers = {
    "Access-Control-Allow-Methods": "POST, OPTIONS",
    "Access-Control-Allow-Headers": "Content-Type",
    "Access-Control-Max-Age": "86400",
  };
  if (originAllowed(origin)) {
    headers["Access-Control-Allow-Origin"] = origin;
    headers.Vary = "Origin";
  }
  return headers;
}

function json(request, body, status) {
  return new Response(JSON.stringify(body), {
    status,
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      ...corsHeaders(request),
    },
  });
}

function clip(text, max) {
  return String(text || "").trim().slice(0, max);
}

function buildMessages(body) {
  const lang = body.lang === "en" ? "en" : "zh";
  const langLine =
    lang === "en"
      ? "Answer in concise English. Explain the solution already on this page. Do not invent a new solution or problem numbers."
      : "用简洁的中文回答。解释本站已经写好的题解，不要另编一套题解，也不要编造不存在的题号。";
  const system = [
    "You are the built-in helper for Doocs LeetCode Wiki.",
    langLine,
    "Ground every claim in the provided page context. If something is missing, say so.",
    "Prefer hints and walkthroughs of the existing code over dumping a replacement.",
    `Page title: ${clip(body.title, 200)}`,
    `Page URL: ${clip(body.url, 300)}`,
    `Active language tab: ${clip(body.langTab, 40) || "(none)"}`,
  ].join("\n");
  const context = [
    `Page excerpt:\n${clip(body.excerpt, 5000) || "(empty)"}`,
    `Current solution (${clip(body.langTab, 40) || "code"}):\n${clip(body.code, 8000) || "(none)"}`,
    body.selection ? `User selection:\n${clip(body.selection, 4000)}` : "",
    body.mine ? `User's own code:\n${clip(body.mine, 8000)}` : "",
  ]
    .filter(Boolean)
    .join("\n\n");
  return [
    { role: "system", content: system },
    { role: "user", content: context },
    { role: "user", content: clip(body.query, 2000) },
  ];
}

async function rateLimited(request, env) {
  const ip = request.headers.get("CF-Connecting-IP") || "local";
  const hour = Math.floor(Date.now() / 3600000);
  const url = new URL(`https://doocs-ai-rate.invalid/${ip}/${hour}`);
  const cache = caches.default;
  const hit = await cache.match(url);
  const used = hit ? Number(await hit.text()) : 0;
  const limit = Number(env.RATE_LIMIT || 30);
  if (used >= limit) {
    return true;
  }
  await cache.put(
    url,
    new Response(String(used + 1), {
      headers: { "Cache-Control": "max-age=3600" },
    })
  );
  return false;
}

export default {
  async fetch(request, env) {
    if (request.method === "OPTIONS") {
      return new Response(null, { status: 204, headers: corsHeaders(request) });
    }
    const path = new URL(request.url).pathname.replace(/\/$/, "") || "/";
    if (path !== "/chat") {
      return json(request, { error: "not found" }, 404);
    }
    if (request.method !== "POST") {
      return json(request, { error: "method not allowed" }, 405);
    }
    const origin = request.headers.get("Origin") || "";
    if (origin && !originAllowed(origin)) {
      return json(request, { error: "forbidden" }, 403);
    }
    if (!env.AI_API_KEY) {
      return json(request, { error: "AI_API_KEY is not configured" }, 503);
    }
    if (await rateLimited(request, env)) {
      return json(request, { error: "rate limited" }, 429);
    }

    let body;
    try {
      body = await request.json();
    } catch {
      return json(request, { error: "invalid json" }, 400);
    }
    if (!clip(body && body.query, 2000)) {
      return json(request, { error: "query required" }, 400);
    }

    const base = String(env.AI_BASE_URL || "https://api.deepseek.com/v1").replace(
      /\/$/,
      ""
    );
    const upstream = await fetch(`${base}/chat/completions`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${env.AI_API_KEY}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        model: env.AI_MODEL || "deepseek-chat",
        stream: true,
        temperature: 0.3,
        messages: buildMessages(body),
      }),
    });

    return new Response(upstream.body, {
      status: upstream.status,
      headers: {
        "Content-Type":
          upstream.headers.get("Content-Type") || "text/event-stream",
        "Cache-Control": "no-store",
        ...corsHeaders(request),
      },
    });
  },
};
