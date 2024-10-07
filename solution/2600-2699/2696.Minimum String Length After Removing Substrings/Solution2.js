const minLength = (s, n = s.length) =>
    ((s = s.replace(/AB|CD/g, '')), s.length === n) ? n : minLength(s);
