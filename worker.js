export default {
    async fetch(request, env) {
        const url = new URL(request.url);
        if (url.pathname.endsWith('/search/search_index.json')) {
            const headers = new Headers(request.headers);
            headers.delete('Accept-Encoding');
            const asset = await env.ASSETS.fetch(
                new Request(new URL(`${url.pathname}.gz`, url.origin), {
                    method: request.method,
                    headers,
                }),
            );
            if (asset.status === 304 || asset.ok) {
                const out = new Headers(asset.headers);
                out.set('Content-Type', 'application/json; charset=utf-8');
                out.set('Cache-Control', 'public, max-age=3600');
                out.delete('Content-Encoding');
                out.delete('Content-Length');
                const body =
                    asset.status === 304 || request.method === 'HEAD' || !asset.body
                        ? null
                        : asset.body.pipeThrough(new DecompressionStream('gzip'));
                return new Response(body, {
                    status: asset.status,
                    headers: out,
                });
            }
        }
        return env.ASSETS.fetch(request);
    },
};
