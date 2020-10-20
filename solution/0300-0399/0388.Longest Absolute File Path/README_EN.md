# [388. Longest Absolute File Path](https://leetcode.com/problems/longest-absolute-file-path)

[中文文档](/solution/0300-0399/0388.Longest%20Absolute%20File%20Path/README.md)

## Description

<p>Suppose we abstract our file system by a string in the following manner:</p>

<p>The string <code>"dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"</code> represents:</p>

<pre>dir

    subdir1

    subdir2

        file.ext

</pre>

<p>The directory <code>dir</code> contains an empty sub-directory <code>subdir1</code> and a sub-directory <code>subdir2</code> containing a file <code>file.ext</code>.</p>

<p>The string <code>"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"</code> represents:</p>

<pre>dir

    subdir1

        file1.ext

        subsubdir1

    subdir2

        subsubdir2

            file2.ext

</pre>

<p>The directory <code>dir</code> contains two sub-directories <code>subdir1</code> and <code>subdir2</code>. <code>subdir1</code> contains a file <code>file1.ext</code> and an empty second-level sub-directory <code>subsubdir1</code>. <code>subdir2</code> contains a second-level sub-directory <code>subsubdir2</code> containing a file <code>file2.ext</code>.</p>

<p>We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is <code>"dir/subdir2/subsubdir2/file2.ext"</code>, and its length is <code>32</code> (not including the double quotes).</p>

<p>Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return <code>0</code>.</p>

<p><b>Note:</b><br />

<ul>

<li>The name of a file contains at least a <code>.</code> and an extension.</li>

<li>The name of a directory or sub-directory will not contain a <code>.</code>.</li>

</ul>

</p>

<p>Time complexity required: <code>O(n)</code> where <code>n</code> is the size of the input string.</p>

<p>Notice that <code>a/aa/aaa/file1.txt</code> is not the longest file path, if there is another path <code>aaaaaaaaaaaaaaaaaaaaa/sth.png</code>.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
