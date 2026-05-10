class Solution:
    def scoreValidator(self, events: list[str]) -> list[int]:
        score = counter = 0
        for event in events:
            if event.isdigit():
                score += int(event)
            elif event == "W":
                counter += 1
                if counter == 10:
                    break
            else:
                score += 1
        return [score, counter]
