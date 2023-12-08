const firebaseModule = (function () {
    async function init() {
        // Your web app's Firebase configuration
        if ('serviceWorker' in navigator) {
            window.addEventListener('load', function() {
                navigator.serviceWorker.register('/firebase-messaging-sw.js')
                    .then(registration => {
                        const firebaseConfig = {
                            apiKey: "AIzaSyBtdsFt2XgsBB-Xe6sHUEDmvG8meeQrU1M",
                            authDomain: "stellar-day-406808.firebaseapp.com",
                            projectId: "stellar-day-406808",
                            storageBucket: "stellar-day-406808.appspot.com",
                            messagingSenderId: "584901529261",
                            appId: "1:584901529261:web:6a17d8704a55fba4c6c024",
                            measurementId: "G-CFMTVGZ1V9"
                        };
                        // Initialize Firebase
                        firebase.initializeApp(firebaseConfig);

                        const messaging = firebase.messaging();
                        messaging.usePublicVapidKey('BEEj-sUx3CA9YK0VvFp1iHJ70PfvphGUA3OQiy9CaFHzZmSXnPaCRohRmwNn4xm-BmSBt1uVhpa4_jTnutKUyBE');

                        messaging.requestPermission()
                            .then(function() {
                                return messaging.getToken();
                            })
                            .then(async function(token) {
                                await fetch('/register', { method: 'post', body: token })
                                console.log("token ::: " + token)
                                messaging.onMessage(payload => {
                                    const title = payload.notification.title
                                    const options = {
                                        body : payload.notification.body
                                    }
                                    navigator.serviceWorker.ready.then(registration => {
                                        registration.showNotification(title, options);
                                    })
                                })
                            })
                            .catch(function(err) {
                                console.log("Error Occured");
                            })
                    })
            })
        }
    }

    return {
        init: function () {
            init()
        }
    }
})()

firebaseModule.init()