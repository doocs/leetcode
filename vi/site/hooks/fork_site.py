"""Settings that only apply when the site is hosted from this fork."""

try:
    from mkdocs import plugins
except ImportError:  # unittest without site deps

    class plugins:
        @staticmethod
        def event_priority(_priority):
            return lambda fn: fn


@plugins.event_priority(-100)
def on_page_markdown(markdown, page, config, files):
    # overrides/partials/comments.html posts to the doocs/leetcode Discussions
    # (giscus, mapped by pathname); pages on the fork must not open threads there.
    page.meta["comments"] = False
    return markdown
