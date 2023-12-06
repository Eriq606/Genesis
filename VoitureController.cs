using Microsoft.AspNetCore.Mvc;

namespace Controllers
{
    [ApiController]
    [Route("[controller]")]

    public class VoitureController : ControllerBase
    {

        [HttpGet(Name = "Voitures")]
        public IEnumerable<Voiture> Get()
        {
            return null;
        }

    }

}
