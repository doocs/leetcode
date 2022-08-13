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
<p><strong>Example:</strong></p>

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

Using list.

```python
class BrowserHistory:
    def __init__(self, homepage: str):
        self.urls = []
        self.cur = -1
        self.tail = -1
        self.visit(homepage)

    def visit(self, url: str) -> None:
        self.cur += 1
        if self.cur < len(self.urls):
            self.urls[self.cur] = url
        else:
            self.urls.append(url)
        self.tail = self.cur

    def back(self, steps: int) -> str:
        self.cur = max(0, self.cur - steps)
        return self.urls[self.cur]

    def forward(self, steps: int) -> str:
        self.cur = min(self.tail, self.cur + steps)
        return self.urls[self.cur]


# Your BrowserHistory object will be instantiated and called as such:
# obj = BrowserHistory(homepage)
# obj.visit(url)
# param_2 = obj.back(steps)
# param_3 = obj.forward(steps)
```

Using stacks.

```python
class BrowserHistory:

    def __init__(self, homepage: str):
        self.s1 = []
        self.s2 = []
        self.cur = homepage

    def visit(self, url: str) -> None:
        self.s2.clear()
        self.s1.append(self.cur)
        self.cur = url

    def back(self, steps: int) -> str:
        while steps > 0 and self.s1:
            self.s2.append(self.cur)
            self.cur = self.s1.pop()
            steps -= 1
        return self.cur

    def forward(self, steps: int) -> str:
        while steps > 0 and self.s2:
            self.s1.append(self.cur)
            self.cur = self.s2.pop()
            steps -= 1
        return self.cur


# Your BrowserHistory object will be instantiated and called as such:
# obj = BrowserHistory(homepage)
# obj.visit(url)
# param_2 = obj.back(steps)
```

### **Java**

Using list.

```java
class BrowserHistory {
    private List<String> urls;
    private int cur = -1;
    private int tail = -1;

    public BrowserHistory(String homepage) {
        urls = new ArrayList<>();
        visit(homepage);
    }

    public void visit(String url) {
        ++cur;
        if (cur < urls.size()) {
            urls.set(cur, url);
        } else {
            urls.add(url);
        }
        tail = cur;
    }

    public String back(int steps) {
        cur = Math.max(0, cur - steps);
        return urls.get(cur);
    }

    public String forward(int steps) {
        cur = Math.min(tail, cur + steps);
        return urls.get(cur);
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

Using stacks.

```java
class BrowserHistory {
    private Deque<String> s1;
    private Deque<String> s2;
    private String cur;

    public BrowserHistory(String homepage) {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
        cur = homepage;
    }

    public void visit(String url) {
        s2.clear();
        s1.push(cur);
        cur = url;
    }

    public String back(int steps) {
        while (steps > 0 && !s1.isEmpty()) {
            s2.push(cur);
            cur = s1.pop();
            --steps;
        }
        return cur;
    }

    public String forward(int steps) {
        while (steps > 0 && !s2.isEmpty()) {
            s1.push(cur);
            cur = s2.pop();
            --steps;
        }
        return cur;
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

### **...**

```

```

<!-- tabs:end -->
