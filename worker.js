export default {
    async fetch(request, env) {
        const url = new URL(request.url);
        if (url.pathname.endsWith('/search/search_index.json')) {
            const asset = await env.ASSETS.fetch(
                new Request(new URL(`${url.pathname}.gz`, url.origin), request),
            );
            if (asset.ok || asset.status === 304) {
                const headers = new Headers(asset.headers);
                headers.set('Content-Type', 'application/json; charset=utf-8');
                headers.set('Cache-Control', 'public, max-age=3600');
                if (asset.status !== 304) {
                    headers.set('Content-Encoding', 'gzip');
                }
                return new Response(asset.body, {
                    status: asset.status,
                    headers,
                    encodeBody: 'manual',
                });
            }
        }
        return env.ASSETS.fetch(request);
    },
};
