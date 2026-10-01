import sys
import unittest
from pathlib import Path

TOOLS = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(TOOLS))
import inventory  # noqa: E402


def unit(target_blob="t1"):
    return inventory.Unit(
        id="lc-0001",
        order=1,
        source="solution/0000-0099/0001.Two Sum/README_EN.md",
        source_blob="s1",
        target="vi/solution/0000-0099/0001.Two Sum/README.md",
        target_blob=target_blob,
    )


def report(state="verified", source="s1", target="t1"):
    return {
        "state": state,
        "source": {"git_blob": source},
        "target": {"git_blob": target},
    }


class InventoryTest(unittest.TestCase):
    def test_ids(self):
        self.assertEqual(inventory.make_id("lc", "0001.Two Sum"), "lc-0001")
        self.assertEqual(inventory.make_id("lcci", "01.01.Is Unique"), "lcci-01.01")
        self.assertEqual(
            inventory.make_id("lcci", "17.26.Sparse Similarity"), "lcci-17.26"
        )

    def test_states(self):
        self.assertEqual(inventory.derive_state(unit(None), None), "pending")
        self.assertEqual(inventory.derive_state(unit(), None), "unreviewed")
        self.assertEqual(inventory.derive_state(unit(), report()), "verified")
        self.assertEqual(inventory.derive_state(unit(), report(source="s0")), "stale")
        self.assertEqual(
            inventory.derive_state(unit(), report(target="t0")), "modified"
        )
        self.assertEqual(
            inventory.derive_state(unit(), report("translating")), "translating"
        )

    def test_incomplete_or_edited_reports(self):
        self.assertEqual(
            inventory.derive_state(unit(), report(source=None)), "unreviewed"
        )
        self.assertEqual(
            inventory.derive_state(unit(), report(target=None)), "unreviewed"
        )
        # a hand edit is not hidden by an upstream change
        self.assertEqual(
            inventory.derive_state(unit(), report(source="s0", target="t0")), "modified"
        )

    def test_blocking_states(self):
        units = []
        for state in (
            "pending",
            "verified",
            "stale",
            "modified",
            "unreviewed",
            "translating",
            "reviewing",
            "blocked",
            "translated",
        ):
            u = unit()
            u.state = state
            units.append(u)
        self.assertEqual(
            [u.state for u in inventory.blocking(units)],
            [
                "modified",
                "unreviewed",
                "translating",
                "reviewing",
                "blocked",
                "translated",
            ],
        )

    def test_list_sources_matches_scope(self):
        units = inventory.list_sources("HEAD")
        ids = [u.id for u in units]
        self.assertEqual(len(ids), len(set(ids)))
        self.assertEqual(units[0].id, "lc-0001")
        self.assertTrue(ids[-1].startswith("lcci-"))
        first = units[0]
        self.assertEqual(first.target, "vi/solution/0000-0099/0001.Two Sum/README.md")
        self.assertEqual(len(first.source_blob), 40)
        self.assertNotIn("solution/README_EN.md", [u.source for u in units])


if __name__ == "__main__":
    unittest.main()
