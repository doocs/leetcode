/**
 * @param {string[]} emails
 * @return {number}
 */
var numUniqueEmails = function (emails) {
    const s = new Set();
    for (const email of emails) {
        const [local, domain] = email.split('@');
        let t = '';
        for (const c of local) {
            if (c === '.') {
                continue;
            }
            if (c === '+') {
                break;
            }
            t += c;
        }
        s.add(t + '@' + domain);
    }
    return s.size;
};
