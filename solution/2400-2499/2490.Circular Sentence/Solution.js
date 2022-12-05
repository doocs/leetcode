var isCircularSentence = function (sentence) {
    const words = sentence.split(' ');
    const post = words[0].charCodeAt(0);
    let prev = words[0].charCodeAt(words[0].length - 1);
    const n = words.length;
    for (let i = 1; i < n; i++) {
        let cur = words[i];
        if (cur.charCodeAt(0) !== prev) {
            return false;
        }
        prev = cur.charCodeAt(cur.length - 1);
    }
    return post === prev;
};
