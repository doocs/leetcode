class Solution:
    def topStudents(
        self,
        positive_feedback: list[str],
        negative_feedback: list[str],
        report: list[str],
        student_id: list[int],
        k: int,
    ) -> list[int]:
        ps = set(positive_feedback)
        ns = set(negative_feedback)
        arr = []
        for sid, r in zip(student_id, report):
            t = 0
            for w in r.split():
                if w in ps:
                    t += 3
                elif w in ns:
                    t -= 1
            arr.append((t, sid))
        arr.sort(key=lambda x: (-x[0], x[1]))
        return [v[1] for v in arr[:k]]
