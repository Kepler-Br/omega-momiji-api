rootProject.name = "omega-momiji-api"

include("api")

findProject(":api")?.name = rootProject.name
