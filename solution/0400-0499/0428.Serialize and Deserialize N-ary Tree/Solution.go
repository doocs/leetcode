/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

type Codec struct {
}

func Constructor() *Codec {
	return &Codec{}
}

func (this *Codec) serialize(root *Node) string {
	if root == nil {
		return ""
	}
	ans := []string{strconv.Itoa(root.Val)}
	q := []*Node{root}
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		for _, child := range node.Children {
			ans = append(ans, strconv.Itoa(child.Val))
			q = append(q, child)
		}
		ans = append(ans, "#")
	}
	return strings.Join(ans, ",")
}

func (this *Codec) deserialize(data string) *Node {
	if data == "" {
		return nil
	}
	vals := strings.Split(data, ",")
	v, _ := strconv.Atoi(vals[0])
	root := &Node{Val: v}
	q := []*Node{root}
	i := 1
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		for i < len(vals) && vals[i] != "#" {
			v, _ = strconv.Atoi(vals[i])
			child := &Node{Val: v}
			node.Children = append(node.Children, child)
			q = append(q, child)
			i++
		}
		i++
	}
	return root
}

/**
 * Your Codec object will be instantiated and called as such:
 * obj := Constructor();
 * data := obj.serialize(root);
 * ans := obj.deserialize(data);
 */
