rootProject.name = "omega-momiji-api"

include("api")
include("gateway-outbound-client-starter")
include("neural-text-client-starter")
include("frontend-client-list-starter")
include("bot-client-list-starter")

findProject(":api")?.name = rootProject.name
