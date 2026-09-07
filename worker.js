export default {
    async fetch(request, env) {
        const url = new URL(request.url);
        if (url.pathname.endsWith('/search/search_index.json')) {
            const asset = await env.ASSETS.fetch(
                new Request(new URL(`${url.pathname}.gz`, url.origin), request),
            );
            if (asset.ok) {
                const headers = new Headers(asset.headers);
                headers.set('Content-Type', 'application/json; charset=utf-8');
                headers.set('Content-Encoding', 'gzip');
                headers.set('Cache-Control', 'public, max-age=3600');
                return new Response(asset.body, {
                    status: 200,
                    headers,
                    encodeBody: 'manual',
                });
            }
        }
        return env.ASSETS.fetch(request);
    },
};
