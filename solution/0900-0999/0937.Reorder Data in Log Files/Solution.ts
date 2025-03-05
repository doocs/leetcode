function reorderLogFiles(logs: string[]): string[] {
    return logs.sort((log1, log2) => {
        const [id1, content1] = log1.split(/ (.+)/);
        const [id2, content2] = log2.split(/ (.+)/);

        const isLetter1 = isNaN(Number(content1[0]));
        const isLetter2 = isNaN(Number(content2[0]));

        if (isLetter1 && isLetter2) {
            const cmp = content1.localeCompare(content2);
            if (cmp !== 0) {
                return cmp;
            }
            return id1.localeCompare(id2);
        }

        return isLetter1 ? -1 : isLetter2 ? 1 : 0;
    });
}
