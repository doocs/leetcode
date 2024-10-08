const minLength = (s: string, n = s.length): number =>
    ((s = s.replace(/AB|CD/g, '')), s.length === n) ? n : minLength(s);
