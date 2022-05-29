function validIPAddress(queryIP: string): string {
    const isIPv4 = () => {
        const ss = queryIP.split('.');
        if (ss.length !== 4) {
            return false;
        }
        for (const s of ss) {
            const num = Number(s);
            if (num < 0 || num > 255 || num + '' !== s) {
                return false;
            }
        }
        return true;
    };
    const isIPv6 = () => {
        const ss = queryIP.split(':');
        if (ss.length !== 8) {
            return false;
        }
        for (const s of ss) {
            if (s.length === 0 || s.length > 4) {
                return false;
            }
            for (const c of s) {
                if (
                    (c >= '0' && c <= '9') ||
                    (c >= 'a' && c <= 'f') ||
                    (c >= 'A' && c <= 'F')
                ) {
                    continue;
                }
                return false;
            }
        }
        return true;
    };
    if (isIPv4()) {
        return 'IPv4';
    }
    if (isIPv6()) {
        return 'IPv6';
    }
    return 'Neither';
}
