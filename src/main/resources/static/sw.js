const cacheName = "cache";
const precacheResources = ["/", "index.html", "favicon.ico"];

self.addEventListener("install", event => {
    console.log("Service worker install event!");
    event.waitUntil(
        caches.open(cacheName).then(cache => {
            return cache.addAll(precacheResources);
        })
    );
});

self.addEventListener("activate", () => {
    console.log("Service worker activate event!");
});

self.addEventListener("fetch", event => {
    console.log("Fetch intercepted for:", event.request.url);
    event.respondWith(
        caches.match(event.request).then(cachedResponse => {
            if (cachedResponse) {
                return cachedResponse;
            }
            return fetch(event.request);
        })
    );
});