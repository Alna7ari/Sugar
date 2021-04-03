package step.ahead.group.sugar.libraries.sse

interface EventHandler {
    fun onOpen()
    fun onMessage(event: MessageEvent)
    fun onError(e: Exception?)
}