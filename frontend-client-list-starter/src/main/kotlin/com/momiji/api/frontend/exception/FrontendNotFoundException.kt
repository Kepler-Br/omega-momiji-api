package com.momiji.api.frontend.exception

class FrontendNotFoundException(frontendName: String) :
    RuntimeException("Frontend \"$frontendName\" not found")
