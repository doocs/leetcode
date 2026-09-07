# Doocs LeetCode AI proxy

站点内置问答的服务端。用户不用填 Key；Key 只放在 Cloudflare Worker 密钥里。

```bash
cd workers/ai-proxy
npx wrangler secret put AI_API_KEY
npx wrangler deploy
```

把部署后的地址写进 `mkdocs.yml`：

```yaml
extra:
  ai:
    enabled: true
    endpoint: https://doocs-leetcode-ai.<your-subdomain>.workers.dev/chat
```

本地预览：

```bash
npx wrangler dev --port 8787
```

页面在 `127.0.0.1` 时会自动走 `http://127.0.0.1:8787/chat`。
