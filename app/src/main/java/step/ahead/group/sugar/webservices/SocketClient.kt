package step.ahead.group.sugar.webservices

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class SocketClient(
    serverURI: URI?,
    httpHeaders: Map<String?, String?>?,
    val onSuccess:()-> Unit,
    val onReceiveRequest:(message: String)-> Unit,
    val onErrorOrClose:(type: String)-> Unit
) : WebSocketClient(serverURI, httpHeaders) {

    override fun onOpen(handshakedata: ServerHandshake) {
        //send("Hello, it is me. Mario :)")
        //Log.d(javaClass.name, "opened connection")
        onSuccess()
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    override fun onMessage(message: String) {
        onReceiveRequest(message)
        //Log.d(javaClass.name,"received: $message")
    }

    override fun onClose(
        code: Int,
        reason: String,
        remote: Boolean
    ) {
        onErrorOrClose("close")
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        //Log.d(javaClass.name, "Connection closed by " + (if (remote) "remote peer" else "us") + " Code: " + code + " Reason: " + reason)
    }

    override fun onError(ex: Exception) {
        onErrorOrClose("error")
        //Log.d(javaClass.name,"onError")
        // if the error is fatal then onClose will be called additionally
    }
}