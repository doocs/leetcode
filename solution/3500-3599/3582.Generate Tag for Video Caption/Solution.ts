function generateTag(caption: string): string {
    const words = caption.trim().split(/\s+/);
    let ans = '#';
    for (let i = 0; i < words.length; i++) {
        const word = words[i].toLowerCase();
        if (i === 0) {
            ans += word;
        } else {
            ans += word.charAt(0).toUpperCase() + word.slice(1);
        }
        if (ans.length >= 100) {
            ans = ans.slice(0, 100);
            break;
        }
    }
    return ans;
}
