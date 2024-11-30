/**
 * @param {number[][]} pairs
 * @return {number[][]}
 */
var validArrangement = function(pairs) {
    const graph = new Map();
    const indegree = new Map();
    const outdegree = new Map();

    for (const [start, end] of pairs) {
        if (!graph.has(start)) graph.set(start, []);
        graph.get(start).push(end);

        outdegree.set(start, (outdegree.get(start) || 0) + 1);
        indegree.set(end, (indegree.get(end) || 0) + 1);
    }

    let startNode = pairs[0][0];
    for (const [node, out] of outdegree) {
        const inCount = indegree.get(node) || 0;
        if (out - inCount === 1) {
            startNode = node;
            break;
        }
    }

    const result = [];
    const stack = [startNode];

    while (stack.length) {
        const node = stack[stack.length - 1];
        if (graph.has(node) && graph.get(node).length > 0) {
            stack.push(graph.get(node).pop());
        } else {
            result.push(stack.pop());
        }
    }

    result.reverse();

    const output = [];
    for (let i = 1; i < result.length; i++) {
        output.push([result[i - 1], result[i]]);
    }

    return output;
};
