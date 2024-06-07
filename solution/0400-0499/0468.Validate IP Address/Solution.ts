function validIPAddress(queryIP: string): string {
    if (isIPv4(queryIP)) {
        return 'IPv4';
    }
    if (isIPv6(queryIP)) {
        return 'IPv6';
    }
    return 'Neither';
}

function isIPv4(s: string): boolean {
    if (s.endsWith('.')) {
        return false;
    }
    const ss = s.split('.');
    if (ss.length !== 4) {
        return false;
    }
    for (const t of ss) {
        if (t.length === 0 || (t.length > 1 && t[0] === '0')) {
            return false;
        }
        const x = convert(t);
        if (x < 0 || x > 255) {
            return false;
        }
    }
    return true;
}

function isIPv6(s: string): boolean {
    if (s.endsWith(':')) {
        return false;
    }
    const ss = s.split(':');
    if (ss.length !== 8) {
        return false;
    }
    for (const t of ss) {
        if (t.length < 1 || t.length > 4) {
            return false;
        }
        for (const c of t) {
            if (!isHexDigit(c)) {
                return false;
            }
        }
    }
    return true;
}

function convert(s: string): number {
    let x = 0;
    for (const c of s) {
        if (!isDigit(c)) {
            return -1;
        }
        x = x * 10 + (c.charCodeAt(0) - '0'.charCodeAt(0));
        if (x > 255) {
            return x;
        }
    }
    return x;
}

function isDigit(c: string): boolean {
    return c >= '0' && c <= '9';
}

function isHexDigit(c: string): boolean {
    return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
}
