# [1472. Design Browser History](https://leetcode.com/problems/design-browser-history)

[中文文档](/solution/1400-1499/1472.Design%20Browser%20History/README.md)

## Description

<p>You have a <strong>browser</strong> of one tab where you start on the <code>homepage</code> and you can visit another <code>url</code>, get back in the history number of <code>steps</code> or move forward in the history number of <code>steps</code>.</p>

<p>Implement the <code>BrowserHistory</code> class:</p>

<ul>
	<li><code>BrowserHistory(string homepage)</code> Initializes the object with the <code>homepage</code>&nbsp;of the browser.</li>
	<li><code>void visit(string url)</code>&nbsp;Visits&nbsp;<code>url</code> from the current page. It clears up all the forward history.</li>
	<li><code>string back(int steps)</code>&nbsp;Move <code>steps</code> back in history. If you can only return <code>x</code> steps in the history and <code>steps &gt; x</code>, you will&nbsp;return only <code>x</code> steps. Return the current <code>url</code>&nbsp;after moving back in history <strong>at most</strong> <code>steps</code>.</li>
	<li><code>string forward(int steps)</code>&nbsp;Move <code>steps</code> forward in history. If you can only forward <code>x</code> steps in the history and <code>steps &gt; x</code>, you will&nbsp;forward only&nbsp;<code>x</code> steps. Return the current <code>url</code>&nbsp;after forwarding in history <strong>at most</strong> <code>steps</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<pre>
<b>Input:</b>
[&quot;BrowserHistory&quot;,&quot;visit&quot;,&quot;visit&quot;,&quot;visit&quot;,&quot;back&quot;,&quot;back&quot;,&quot;forward&quot;,&quot;visit&quot;,&quot;forward&quot;,&quot;back&quot;,&quot;back&quot;]
[[&quot;leetcode.com&quot;],[&quot;google.com&quot;],[&quot;facebook.com&quot;],[&quot;youtube.com&quot;],[1],[1],[1],[&quot;linkedin.com&quot;],[2],[2],[7]]
<b>Output:</b>
[null,null,null,null,&quot;facebook.com&quot;,&quot;google.com&quot;,&quot;facebook.com&quot;,null,&quot;linkedin.com&quot;,&quot;google.com&quot;,&quot;leetcode.com&quot;]

<b>Explanation:</b>
BrowserHistory browserHistory = new BrowserHistory(&quot;leetcode.com&quot;);
browserHistory.visit(&quot;google.com&quot;);       // You are in &quot;leetcode.com&quot;. Visit &quot;google.com&quot;
browserHistory.visit(&quot;facebook.com&quot;);     // You are in &quot;google.com&quot;. Visit &quot;facebook.com&quot;
browserHistory.visit(&quot;youtube.com&quot;);      // You are in &quot;facebook.com&quot;. Visit &quot;youtube.com&quot;
browserHistory.back(1);                   // You are in &quot;youtube.com&quot;, move back to &quot;facebook.com&quot; return &quot;facebook.com&quot;
browserHistory.back(1);                   // You are in &quot;facebook.com&quot;, move back to &quot;google.com&quot; return &quot;google.com&quot;
browserHistory.forward(1);                // You are in &quot;google.com&quot;, move forward to &quot;facebook.com&quot; return &quot;facebook.com&quot;
browserHistory.visit(&quot;linkedin.com&quot;);     // You are in &quot;facebook.com&quot;. Visit &quot;linkedin.com&quot;
browserHistory.forward(2);                // You are in &quot;linkedin.com&quot;, you cannot move forward any steps.
browserHistory.back(2);                   // You are in &quot;linkedin.com&quot;, move back two steps to &quot;facebook.com&quot; then to &quot;google.com&quot;. return &quot;google.com&quot;
browserHistory.back(7);                   // You are in &quot;google.com&quot;, you can move back only one step to &quot;leetcode.com&quot;. return &quot;leetcode.com&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= homepage.length &lt;= 20</code></li>
	<li><code>1 &lt;= url.length &lt;= 20</code></li>
	<li><code>1 &lt;= steps &lt;= 100</code></li>
	<li><code>homepage</code> and <code>url</code> consist of&nbsp; &#39;.&#39; or lower case English letters.</li>
	<li>At most <code>5000</code>&nbsp;calls will be made to <code>visit</code>, <code>back</code>, and <code>forward</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class BrowserHistory:

    def __init__(self, homepage: str):
        self.stk1 = []
        self.stk2 = []
        self.visit(homepage)

    def visit(self, url: str) -> None:
        self.stk1.append(url)
        self.stk2.clear()

    def back(self, steps: int) -> str:
        while steps and len(self.stk1) > 1:
            self.stk2.append(self.stk1.pop())
            steps -= 1
        return self.stk1[-1]

    def forward(self, steps: int) -> str:
        while steps and self.stk2:
            self.stk1.append(self.stk2.pop())
            steps -= 1
        return self.stk1[-1]


# Your BrowserHistory object will be instantiated and called as such:
# obj = BrowserHistory(homepage)
# obj.visit(url)
# param_2 = obj.back(steps)
# param_3 = obj.forward(steps)
```

### **Java**

```java
class BrowserHistory {
    private Deque<String> stk1 = new ArrayDeque<>();
    private Deque<String> stk2 = new ArrayDeque<>();

    public BrowserHistory(String homepage) {
        visit(homepage);
    }

    public void visit(String url) {
        stk1.push(url);
        stk2.clear();
    }

    public String back(int steps) {
        for (; steps > 0 && stk1.size() > 1; --steps) {
            stk2.push(stk1.pop());
        }
        return stk1.peek();
    }

    public String forward(int steps) {
        for (; steps > 0 && !stk2.isEmpty(); --steps) {
            stk1.push(stk2.pop());
        }
        return stk1.peek();
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
```

### **C++**

```cpp
class BrowserHistory {
public:
    stack<string> stk1;
    stack<string> stk2;

    BrowserHistory(string homepage) {
        visit(homepage);
    }

    void visit(string url) {
        stk1.push(url);
        stk2 = stack<string>();
    }

    string back(int steps) {
        for (; steps && stk1.size() > 1; --steps) {
            stk2.push(stk1.top());
            stk1.pop();
        }
        return stk1.top();
    }

    string forward(int steps) {
        for (; steps && !stk2.empty(); --steps) {
            stk1.push(stk2.top());
            stk2.pop();
        }
        return stk1.top();
    }
};

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory* obj = new BrowserHistory(homepage);
 * obj->visit(url);
 * string param_2 = obj->back(steps);
 * string param_3 = obj->forward(steps);
 */
```

### **Go**

```go
type BrowserHistory struct {
	stk1 []string
	stk2 []string
}

func Constructor(homepage string) BrowserHistory {
	t := BrowserHistory{[]string{}, []string{}}
	t.Visit(homepage)
	return t
}

func (this *BrowserHistory) Visit(url string) {
	this.stk1 = append(this.stk1, url)
	this.stk2 = []string{}
}

func (this *BrowserHistory) Back(steps int) string {
	for i := 0; i < steps && len(this.stk1) > 1; i++ {
		this.stk2 = append(this.stk2, this.stk1[len(this.stk1)-1])
		this.stk1 = this.stk1[:len(this.stk1)-1]
	}
	return this.stk1[len(this.stk1)-1]
}

func (this *BrowserHistory) Forward(steps int) string {
	for i := 0; i < steps && len(this.stk2) > 0; i++ {
		this.stk1 = append(this.stk1, this.stk2[len(this.stk2)-1])
		this.stk2 = this.stk2[:len(this.stk2)-1]
	}
	return this.stk1[len(this.stk1)-1]
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * obj := Constructor(homepage);
 * obj.Visit(url);
 * param_2 := obj.Back(steps);
 * param_3 := obj.Forward(steps);
 */
```

### **...**

```

```

<!-- tabs:end -->
