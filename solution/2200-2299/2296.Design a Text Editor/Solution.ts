class TextEditor {
    private left: string[];
    private right: string[];

    constructor() {
        this.left = [];
        this.right = [];
    }

    addText(text: string): void {
        this.left.push(...text);
    }

    deleteText(k: number): number {
        k = Math.min(k, this.left.length);
        for (let i = 0; i < k; i++) {
            this.left.pop();
        }
        return k;
    }

    cursorLeft(k: number): string {
        k = Math.min(k, this.left.length);
        for (let i = 0; i < k; i++) {
            this.right.push(this.left.pop()!);
        }
        return this.left.slice(-10).join('');
    }

    cursorRight(k: number): string {
        k = Math.min(k, this.right.length);
        for (let i = 0; i < k; i++) {
            this.left.push(this.right.pop()!);
        }
        return this.left.slice(-10).join('');
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * var obj = new TextEditor()
 * obj.addText(text)
 * var param_2 = obj.deleteText(k)
 * var param_3 = obj.cursorLeft(k)
 * var param_4 = obj.cursorRight(k)
 */
