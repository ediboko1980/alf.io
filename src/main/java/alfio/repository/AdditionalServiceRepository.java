/**
 * This file is part of alf.io.
 *
 * alf.io is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * alf.io is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with alf.io.  If not, see <http://www.gnu.org/licenses/>.
 */
package alfio.repository;

import alfio.model.AdditionalService;
import ch.digitalfondue.npjt.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@QueryRepository
public interface AdditionalServiceRepository {

    @Query("select * from additional_service where event_id_fk = :eventId order by ordinal")
    List<AdditionalService> loadAllForEvent(@Bind("eventId") int eventId);

    @Query("select * from additional_service where id = :id and event_id_fk = :eventId")
    AdditionalService getById(@Bind("id") int id, @Bind("eventId") int eventId);

    @Query("delete from additional_service where id = :id and event_id_fk = :eventId")
    int delete(@Bind("id") int id, @Bind("eventId") int eventId);

    @Query("insert into additional_service (event_id_fk, price_cts, fix_price, ordinal, available_qty, max_qty_per_order, inception_ts, expiration_ts, vat, vat_type) " +
        "values(:eventId, :priceCts, :fixPrice, :ordinal, :availableQty, :maxQtyPerOrder, :inceptionTs, :expirationTs, :vat, :vatType)")
    @AutoGeneratedKey("id")
    AffectedRowCountAndKey<Integer> insert(@Bind("eventId") int eventId, @Bind("priceCts") int priceInCents, @Bind("fixPrice") boolean fixPrice,
                                           @Bind("ordinal") int ordinal, @Bind("availableQty") int availableQuantity, @Bind("maxQtyPerOrder") int maxQtyPerOrder,
                                           @Bind("inceptionTs") ZonedDateTime inception, @Bind("expirationTs") ZonedDateTime expiration, @Bind("vat") BigDecimal vat,
                                           @Bind("vatType") AdditionalService.VatType vatType);

    @Query("update additional_service set price_cts = :priceCts, fix_price = :fixPrice, ordinal = :ordinal, available_qty = :availableQty, max_qty_per_order = :maxQtyPerOrder," +
        " inception_ts = :inceptionTs, expiration_ts = :expirationTs, vat = :vat, vat_type = :vatType where id = :id")
    int update(@Bind("id") int id, @Bind("priceCts") int priceInCents, @Bind("fixPrice") boolean fixPrice,
               @Bind("ordinal") int ordinal, @Bind("availableQty") int availableQuantity, @Bind("maxQtyPerOrder") int maxQtyPerOrder,
               @Bind("inceptionTs") ZonedDateTime inception, @Bind("expirationTs") ZonedDateTime expiration, @Bind("vat") BigDecimal vat,
               @Bind("vatType") AdditionalService.VatType vatType);

}