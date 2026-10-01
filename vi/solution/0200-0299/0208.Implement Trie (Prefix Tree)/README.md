---
comments: true
difficulty: Medium
tags:
    - Design
    - Trie
    - Hash Table
    - String
---

<!-- problem:start -->

# [208. Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree)

[中文文档](/solution/0200-0299/0208.Implement%20Trie%20%28Prefix%20Tree%29/README.md)

## Mô tả

<!-- description:start -->

<p><a href="https://en.wikipedia.org/wiki/Trie" target="_blank"><strong>Trie</strong></a> (đọc là "try"), hay <strong>cây tiền tố</strong>, là một cấu trúc dữ liệu dạng cây được dùng để lưu trữ và truy xuất hiệu quả các khóa trong một tập dữ liệu gồm các chuỗi. Cấu trúc dữ liệu này có nhiều ứng dụng, chẳng hạn như tự động hoàn thành và kiểm tra chính tả.</p>

<p>Hãy cài đặt lớp Trie:</p>

<ul>
	<li><code>Trie()</code> Khởi tạo đối tượng trie.</li>
	<li><code>void insert(String word)</code> Chèn chuỗi <code>word</code> vào trie.</li>
	<li><code>boolean search(String word)</code> Trả về <code>true</code> nếu chuỗi <code>word</code> có trong trie (nghĩa là đã được chèn trước đó), và <code>false</code> trong trường hợp ngược lại.</li>
	<li><code>boolean startsWith(String prefix)</code> Trả về <code>true</code> nếu có một chuỗi <code>word</code> đã được chèn trước đó có tiền tố là <code>prefix</code>, và <code>false</code> trong trường hợp ngược lại.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào</strong>
[&quot;Trie&quot;, &quot;insert&quot;, &quot;search&quot;, &quot;search&quot;, &quot;startsWith&quot;, &quot;insert&quot;, &quot;search&quot;]
[[], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;]]
<strong>Đầu ra</strong>
[null, null, true, false, true, null, true]

<strong>Giải thích</strong>
Trie trie = new Trie();
trie.insert(&quot;apple&quot;);
trie.search(&quot;apple&quot;);   // return True
trie.search(&quot;app&quot;);     // return False
trie.startsWith(&quot;app&quot;); // return True
trie.insert(&quot;app&quot;);
trie.search(&quot;app&quot;);     // return True
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> và <code>prefix</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
	<li>Sẽ có nhiều nhất <code>3 * 10<sup>4</sup></code> lời gọi <strong>tổng cộng</strong> đến <code>insert</code>, <code>search</code> và <code>startsWith</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Trie (Cây tiền tố)

<!-- thinking:start -->

> **Tư duy**
>
> Việc duyệt qua mọi từ đã chèn cho mỗi truy vấn tiền tố sẽ lặp lại công việc trên các tiền tố chung. Vì các chữ cái đều là chữ thường, ta có thể dùng một cây $26$ nhánh để xếp các ký tự theo từng lớp.
>
> Thao tác chèn duyệt qua các nút và tạo chúng khi cần, đồng thời đánh dấu $isEnd$ tại nút cuối cùng. Tìm kiếm tiền tố đi theo cùng một đường đi; tìm kiếm toàn bộ từ còn kiểm tra cả cờ kết thúc.

<!-- thinking:end -->

Mỗi nút trong trie gồm hai phần:

1. Một mảng con trỏ tới các nút con `children`. Với bài toán này, mảng có độ dài 26, tương ứng với số chữ cái tiếng Anh viết thường. `children[0]` tương ứng với chữ cái viết thường 'a', ..., và `children[25]` tương ứng với chữ cái viết thường 'z'.
2. Một trường boolean `isEnd` cho biết nút có phải là cuối của một chuỗi hay không.

### 1. Chèn một chuỗi

Bắt đầu từ nút gốc của trie và chèn chuỗi. Với nút con tương ứng với ký tự hiện tại, có hai trường hợp:

- Nút con tồn tại. Di chuyển theo con trỏ đến nút con đó và tiếp tục xử lý ký tự tiếp theo.
- Nút con không tồn tại. Tạo một nút con mới, ghi nhận nó tại vị trí tương ứng trong mảng `children`, sau đó di chuyển theo con trỏ đến nút con và tiếp tục tìm kiếm ký tự tiếp theo.

Lặp lại các bước trên cho đến khi xử lý ký tự cuối cùng của chuỗi, sau đó đánh dấu nút hiện tại là nút cuối của chuỗi.

### 2. Tìm kiếm một tiền tố

Bắt đầu từ nút gốc của trie và tìm kiếm tiền tố. Với nút con tương ứng với ký tự hiện tại, có hai trường hợp:

- Nút con tồn tại. Di chuyển theo con trỏ đến nút con đó và tiếp tục tìm kiếm ký tự tiếp theo.
- Nút con không tồn tại. Điều này có nghĩa là trie không chứa tiền tố đó, vì vậy trả về một con trỏ null.

Lặp lại các bước trên cho đến khi trả về một con trỏ null hoặc tìm kiếm xong ký tự cuối cùng của tiền tố.

Nếu tìm kiếm đến cuối tiền tố, điều đó có nghĩa là trie chứa tiền tố. Ngoài ra, nếu `isEnd` của nút tương ứng với cuối tiền tố là true, điều đó có nghĩa là trie chứa chuỗi đó.

Độ phức tạp thời gian khi chèn một chuỗi là $O(m \times |\Sigma|)$, còn độ phức tạp thời gian khi tìm kiếm một tiền tố là $O(m)$, trong đó $m$ là độ dài chuỗi và $|\Sigma|$ là kích thước của tập ký tự (26 trong bài toán này). Độ phức tạp không gian là $O(q \times m \times |\Sigma|)$, trong đó $q$ là số chuỗi đã chèn.

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, word: str) -> None:
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word: str) -> bool:
        node = self._search_prefix(word)
        return node is not None and node.is_end

    def startsWith(self, prefix: str) -> bool:
        node = self._search_prefix(prefix)
        return node is not None

    def _search_prefix(self, prefix: str):
        node = self
        for c in prefix:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return None
            node = node.children[idx]
        return node


# Đối tượng Trie của bạn sẽ được khởi tạo và gọi như sau:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)
```

#### Java

```java
class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
    }

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String s) {
        Trie node = this;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}

/**
 * Đối tượng Trie của bạn sẽ được khởi tạo và gọi như sau:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

#### C++

```cpp
class Trie {
private:
    vector<Trie*> children;
    bool isEnd;

    Trie* searchPrefix(string s) {
        Trie* node = this;
        for (char c : s) {
            int idx = c - 'a';
            if (!node->children[idx]) return nullptr;
            node = node->children[idx];
        }
        return node;
    }

public:
    Trie()
        : children(26)
        , isEnd(false) {}

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    bool search(string word) {
        Trie* node = searchPrefix(word);
        return node != nullptr && node->isEnd;
    }

    bool startsWith(string prefix) {
        Trie* node = searchPrefix(prefix);
        return node != nullptr;
    }
};

/**
 * Đối tượng Trie của bạn sẽ được khởi tạo và gọi như sau:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */
```

#### Go

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func Constructor() Trie {
	return Trie{}
}

func (this *Trie) Insert(word string) {
	node := this
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (this *Trie) Search(word string) bool {
	node := this.SearchPrefix(word)
	return node != nil && node.isEnd
}

func (this *Trie) StartsWith(prefix string) bool {
	node := this.SearchPrefix(prefix)
	return node != nil
}

func (this *Trie) SearchPrefix(s string) *Trie {
	node := this
	for _, c := range s {
		idx := c - 'a'
		if node.children[idx] == nil {
			return nil
		}
		node = node.children[idx]
	}
	return node
}

/**
 * Đối tượng Trie của bạn sẽ được khởi tạo và gọi như sau:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.Search(word);
 * param_3 := obj.StartsWith(prefix);
 */
```

#### TypeScript

```ts
class TrieNode {
    children;
    isEnd;
    constructor() {
        this.children = new Array(26);
        this.isEnd = false;
    }
}

class Trie {
    root;
    constructor() {
        this.root = new TrieNode();
    }

    insert(word: string): void {
        let head = this.root;
        for (let char of word) {
            let index = char.charCodeAt(0) - 97;
            if (!head.children[index]) {
                head.children[index] = new TrieNode();
            }
            head = head.children[index];
        }
        head.isEnd = true;
    }

    search(word: string): boolean {
        let head = this.searchPrefix(word);
        return head != null && head.isEnd;
    }

    startsWith(prefix: string): boolean {
        return this.searchPrefix(prefix) != null;
    }

    private searchPrefix(prefix: string) {
        let head = this.root;
        for (let char of prefix) {
            let index = char.charCodeAt(0) - 97;
            if (!head.children[index]) return null;
            head = head.children[index];
        }
        return head;
    }
}
```

#### Rust

```rust
use std::{cell::RefCell, collections::HashMap, rc::Rc};

struct TrieNode {
    pub val: Option<char>,
    pub flag: bool,
    pub child: HashMap<char, Rc<RefCell<TrieNode>>>,
}

impl TrieNode {
    fn new() -> Self {
        Self {
            val: None,
            flag: false,
            child: HashMap::new(),
        }
    }

    fn new_with_val(val: char) -> Self {
        Self {
            val: Some(val),
            flag: false,
            child: HashMap::new(),
        }
    }
}

struct Trie {
    root: Rc<RefCell<TrieNode>>,
}

/// Đối tượng Trie của bạn sẽ được khởi tạo và gọi như sau:
/// let obj = Trie::new();
/// obj.insert(word);
/// let ret_2: bool = obj.search(word);
/// let ret_3: bool = obj.starts_with(prefix);
impl Trie {
    fn new() -> Self {
        Self {
            root: Rc::new(RefCell::new(TrieNode::new())),
        }
    }

    fn insert(&self, word: String) {
        let char_vec: Vec<char> = word.chars().collect();
        // Lấy bản sao của nút gốc hiện tại
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                // Cần tạo mục nhập thủ công
                root.borrow_mut()
                    .child
                    .insert(*c, Rc::new(RefCell::new(TrieNode::new())));
            }
            // Lấy nút con
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        {
            root.borrow_mut().flag = true;
        }
    }

    fn search(&self, word: String) -> bool {
        let char_vec: Vec<char> = word.chars().collect();
        // Lấy bản sao của nút gốc hiện tại
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                return false;
            }
            // Lấy nút con
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        let flag = root.borrow().flag;
        flag
    }

    fn starts_with(&self, prefix: String) -> bool {
        let char_vec: Vec<char> = prefix.chars().collect();
        // Lấy bản sao của nút gốc hiện tại
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                return false;
            }
            // Lấy nút con
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * Khởi tạo cấu trúc dữ liệu của bạn tại đây.
 */
var Trie = function () {
    this.children = {};
};

/**
 * Chèn một từ vào trie.
 * @param {string} word
 * @return {void}
 */
Trie.prototype.insert = function (word) {
    let node = this.children;
    for (let char of word) {
        if (!node[char]) {
            node[char] = {};
        }
        node = node[char];
    }
    node.isEnd = true;
};

/**
 * Trả về việc từ có nằm trong trie hay không.
 * @param {string} word
 * @return {boolean}
 */
Trie.prototype.search = function (word) {
    let node = this.searchPrefix(word);
    return node != undefined && node.isEnd != undefined;
};

Trie.prototype.searchPrefix = function (prefix) {
    let node = this.children;
    for (let char of prefix) {
        if (!node[char]) return false;
        node = node[char];
    }
    return node;
};

/**
 * Trả về việc có từ nào trong trie bắt đầu bằng tiền tố đã cho hay không.
 * @param {string} prefix
 * @return {boolean}
 */
Trie.prototype.startsWith = function (prefix) {
    return this.searchPrefix(prefix);
};

/**
 * Đối tượng Trie của bạn sẽ được khởi tạo và gọi như sau:
 * var obj = new Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */
```

#### C#

```cs
public class Trie {
    bool isEnd;
    Trie[] children = new Trie[26];

    public Trie() {

    }

    public void Insert(string word) {
        Trie node = this;
        foreach (var c in word) {
            var idx = c - 'a';
            node.children[idx] ??= new Trie();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public bool Search(string word) {
        Trie node = SearchPrefix(word);
        return node != null && node.isEnd;
    }

    public bool StartsWith(string prefix) {
        Trie node = SearchPrefix(prefix);
        return node != null;
    }

    private Trie SearchPrefix(string s) {
        Trie node = this;
        foreach (var c in s) {
            var idx = c - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}

/**
 * Đối tượng Trie của bạn sẽ được khởi tạo và gọi như sau:
 * Trie obj = new Trie();
 * obj.Insert(word);
 * bool param_2 = obj.Search(word);
 * bool param_3 = obj.StartsWith(prefix);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
