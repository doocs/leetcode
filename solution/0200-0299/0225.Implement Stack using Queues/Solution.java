    class MyStack {

        Queue<Integer> q1;
        Queue<Integer> q2;

        /** Initialize your data structure here. */
        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            q1.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (q1.isEmpty()) {
                return 0;
            }
            while (q1.size() > 1) {
                q2.add(q1.poll());
            }
            int result = q1.poll();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return result;
        }

        /** Get the top element. */
        public int top() {
            if (q1.isEmpty()) {
                return 0;
            }
            while (q1.size() > 1) {
                q2.add(q1.poll());
            }
            int result = q1.poll();
            q2.add(result);
            while (q2.size() > 0) {
                q1.add(q2.poll());
            }
            return result;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }